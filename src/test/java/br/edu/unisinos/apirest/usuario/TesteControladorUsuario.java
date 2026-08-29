package br.edu.unisinos.apirest.usuario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest @AutoConfigureMockMvc class TesteControladorUsuario {
    @Autowired MockMvc mvc; @Autowired RepositorioUsuario repository; @BeforeEach void clean(){ repository.deleteAll(); }
    @Test void shouldCreateWithoutExposingPassword() throws Exception {
        mvc.perform(post("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON).content("""
            {"nome":"Ana","cpf":"12345678901","email":"ana@email.com","telefone":"51999999999","senha":"segredo123","perfil":"ADOTANTE",
            "endereco":{"logradouro":"Rua B","numero":"20","bairro":"Centro","cidade":"Canoas","estado":"RS","cep":"92000-000"}}
            """))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.nome").value("Ana"))
            .andExpect(jsonPath("$.senha").doesNotExist()).andExpect(jsonPath("$.hashSenha").doesNotExist());
    }
}

