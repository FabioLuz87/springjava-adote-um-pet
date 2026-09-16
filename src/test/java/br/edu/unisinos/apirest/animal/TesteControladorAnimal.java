package br.edu.unisinos.apirest.animal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.edu.unisinos.apirest.endereco.DadosEndereco;
import br.edu.unisinos.apirest.entidade.Entidade;
import br.edu.unisinos.apirest.entidade.RepositorioEntidade;
import br.edu.unisinos.apirest.entidade.RequisicaoEntidade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class TesteControladorAnimal {

    @Autowired
    MockMvc mvc;

    @Autowired
    RepositorioEntidade entidades;

    @Autowired
    RepositorioAnimal animais;

    @BeforeEach
    void prepararBanco() {
        animais.deleteAll();
        entidades.deleteAll();
    }

    @Test
    void deveCriarEConsultarAnimal() throws Exception {
        Entidade entidade = criarEntidade();

        String localizacao = mvc.perform(post("/api/v1/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAnimal("Rex", "DISPONIVEL", entidade.getId())))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.nome").value("Rex"))
                .andExpect(jsonPath("$.idEntidade").value(entidade.getId()))
                .andReturn()
                .getResponse()
                .getHeader("Location");

        mvc.perform(get(localizacao))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Rex"))
                .andExpect(jsonPath("$.especie").value("Cão"))
                .andExpect(jsonPath("$.status").value("DISPONIVEL"));
    }

    @Test
    void deveListarAnimais() throws Exception {
        Entidade entidade = criarEntidade();
        criarAnimal("Rex", entidade.getId());
        criarAnimal("Luna", entidade.getId());

        mvc.perform(get("/api/v1/animais"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.totalElements").value(2));
    }

    @Test
    void deveAtualizarAnimal() throws Exception {
        Entidade entidade = criarEntidade();
        String localizacao = criarAnimal("Rex", entidade.getId());

        mvc.perform(put(localizacao)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAnimal("Rex Atualizado", "ADOTADO", entidade.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Rex Atualizado"))
                .andExpect(jsonPath("$.status").value("ADOTADO"));

        mvc.perform(get(localizacao))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Rex Atualizado"))
                .andExpect(jsonPath("$.status").value("ADOTADO"));
    }

    @Test
    void deveExcluirAnimal() throws Exception {
        Entidade entidade = criarEntidade();
        String localizacao = criarAnimal("Rex", entidade.getId());

        mvc.perform(delete(localizacao))
                .andExpect(status().isNoContent());

        mvc.perform(get(localizacao))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Recurso não encontrado"));
    }

    @Test
    void deveRejeitarAnimalComDadosInvalidos() throws Exception {
        mvc.perform(post("/api/v1/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"","especie":"","raca":"","sexo":"","idade":-1,"peso":0,
                                 "descricao":"Inválido","status":"","idEntidade":0}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Dados inválidos"))
                .andExpect(jsonPath("$.fields.nome").exists())
                .andExpect(jsonPath("$.fields.idade").exists())
                .andExpect(jsonPath("$.fields.peso").exists())
                .andExpect(jsonPath("$.fields.idEntidade").exists());
    }

    @Test
    void deveRetornarNaoEncontradoQuandoEntidadeNaoExiste() throws Exception {
        mvc.perform(post("/api/v1/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAnimal("Rex", "DISPONIVEL", 999_999L)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Recurso não encontrado"))
                .andExpect(jsonPath("$.detail").value("Entidade não encontrada."));
    }

    private Entidade criarEntidade() {
        DadosEndereco endereco = new DadosEndereco(
                "Rua dos Animais",
                "1",
                null,
                "Centro",
                "Canoas",
                "RS",
                "92000-000");
        RequisicaoEntidade requisicao = new RequisicaoEntidade(
                "Abrigo Amigo",
                "ONG",
                "51999999999",
                "abrigo@exemplo.com",
                "9-18",
                endereco);
        return entidades.save(new Entidade(requisicao));
    }

    private String criarAnimal(String nome, Long idEntidade) throws Exception {
        return mvc.perform(post("/api/v1/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAnimal(nome, "DISPONIVEL", idEntidade)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");
    }

    private String jsonAnimal(String nome, String status, Long idEntidade) {
        return """
                {"nome":"%s","especie":"Cão","raca":"SRD","sexo":"Macho","idade":3,"peso":12.5,
                 "descricao":"Dócil","status":"%s","idEntidade":%d}
                """.formatted(nome, status, idEntidade);
    }
}
