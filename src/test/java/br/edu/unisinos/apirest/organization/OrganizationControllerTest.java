package br.edu.unisinos.apirest.organization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest @AutoConfigureMockMvc
class OrganizationControllerTest {
    @Autowired MockMvc mvc;
    @Autowired OrganizationRepository repository;
    @BeforeEach void clean() { repository.deleteAll(); }

    @Test void shouldCompleteCrud() throws Exception {
        String body = """
                {"name":"Abrigo Amigo","type":"ONG","phone":"51999999999","email":"contato@abrigo.org",
                 "serviceHours":"Seg-Sex 9h-18h","address":{"street":"Rua A","number":"10","complement":null,
                 "neighborhood":"Centro","city":"São Leopoldo","state":"RS","zipCode":"93000-000"}}
                """;
        String location = mvc.perform(post("/api/v1/entidades").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.name").value("Abrigo Amigo"))
                .andReturn().getResponse().getHeader("Location");
        mvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.address.state").value("RS"));
        mvc.perform(delete(location)).andExpect(status().isNoContent());
        mvc.perform(get(location)).andExpect(status().isNotFound());
    }
}
