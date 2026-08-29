package br.edu.unisinos.apirest.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateAndFindProduct() throws Exception {
        String location = mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "sku": "NOTE-001",
                                  "name": "Notebook",
                                  "price": 3999.90
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.sku").value("NOTE-001"))
                .andExpect(jsonPath("$.name").value("Notebook"))
                .andReturn()
                .getResponse()
                .getHeader("Location");

        mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sku").value("NOTE-001"))
                .andExpect(jsonPath("$.price").value(3999.90));
    }

    @Test
    void shouldRejectInvalidProduct() throws Exception {
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "sku": "",
                                  "name": "",
                                  "price": -1
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Dados inválidos"))
                .andExpect(jsonPath("$.fields.sku").exists())
                .andExpect(jsonPath("$.fields.name").exists())
                .andExpect(jsonPath("$.fields.price").exists());
    }

    @Test
    void shouldReturnNotFoundForUnknownProduct() throws Exception {
        mockMvc.perform(get("/api/v1/products/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Recurso não encontrado"))
                .andExpect(jsonPath("$.detail").value("Produto não encontrado."));
    }
}
