package br.edu.unisinos.apirest.animal;
import br.edu.unisinos.apirest.address.AddressData; import br.edu.unisinos.apirest.organization.*; import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class AnimalControllerTest{
 @Autowired MockMvc mvc; @Autowired OrganizationRepository organizations; @Autowired AnimalRepository animals;
 @BeforeEach void setup(){animals.deleteAll();organizations.deleteAll();}
 @Test void shouldAssociateAnimalWithOrganization()throws Exception{
  Organization o=organizations.save(new Organization(new OrganizationRequest("Abrigo","ONG","51999","a@b.com","9-18",new AddressData("Rua","1",null,"Centro","Canoas","RS","92000-000"))));
  mvc.perform(post("/api/v1/animais").contentType(MediaType.APPLICATION_JSON).content("""
   {"name":"Rex","species":"Cão","breed":"SRD","sex":"Macho","age":3,"weight":12.5,"description":"Dócil","status":"DISPONIVEL","organizationId":%d}
   """.formatted(o.getId()))).andExpect(status().isCreated()).andExpect(jsonPath("$.organizationId").value(o.getId()));
 }
}
