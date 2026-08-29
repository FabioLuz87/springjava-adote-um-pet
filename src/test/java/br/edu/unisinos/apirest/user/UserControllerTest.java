package br.edu.unisinos.apirest.user;
import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class UserControllerTest {
    @Autowired MockMvc mvc; @Autowired UserRepository repository; @BeforeEach void clean(){ repository.deleteAll(); }
    @Test void shouldCreateWithoutExposingPassword() throws Exception {
        mvc.perform(post("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON).content("""
            {"name":"Ana","cpf":"12345678901","email":"ana@email.com","phone":"51999999999","password":"segredo123","profile":"ADOTANTE",
            "address":{"street":"Rua B","number":"20","neighborhood":"Centro","city":"Canoas","state":"RS","zipCode":"92000-000"}}
            """))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.name").value("Ana"))
            .andExpect(jsonPath("$.password").doesNotExist()).andExpect(jsonPath("$.passwordHash").doesNotExist());
    }
}
