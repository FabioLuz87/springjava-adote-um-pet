package br.edu.unisinos.apirest.entidade;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

@SpringBootTest
@AutoConfigureMockMvc
class TesteControladorEntidade {
    @Autowired
    MockMvc mvc;
    @Autowired
    RepositorioEntidade repository;

    @BeforeEach
    void clean() {
        repository.deleteAll();
    }

    @Test
    void shouldCompleteCrud() throws Exception {
        String body = """
                {"nome":"Abrigo Amigo","tipo":"ONG","telefone":"51999999999","email":"contato@abrigo.org",
                 "horarioAtendimento":"Seg-Sex 9h-18h","endereco":{"logradouro":"Rua A","numero":"10","complemento":null,
                 "bairro":"Centro","cidade":"São Leopoldo","estado":"RS","cep":"93000-000"}}
                """;
        String location = mvc.perform(post("/api/v1/entidades").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Abrigo Amigo"))
                .andReturn().getResponse().getHeader("Location");
        mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.endereco.estado").value("RS"));
        mvc.perform(delete(location)).andExpect(status().isNoContent());
        mvc.perform(get(location)).andExpect(status().isNotFound());
    }
}

