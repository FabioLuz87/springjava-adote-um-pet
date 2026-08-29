package br.edu.unisinos.apirest.adoption;
import br.edu.unisinos.apirest.address.AddressData; import br.edu.unisinos.apirest.animal.*; import br.edu.unisinos.apirest.organization.*; import br.edu.unisinos.apirest.user.*; import java.math.BigDecimal; import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.http.MediaType; import org.springframework.test.annotation.DirtiesContext; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc @DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_CLASS) class AdoptionControllerTest{
 @Autowired MockMvc mvc; @Autowired AdoptionRepository adoptions; @Autowired AnimalRepository animals; @Autowired UserRepository users; @Autowired OrganizationRepository organizations;
 @BeforeEach void clean(){adoptions.deleteAll();animals.deleteAll();users.deleteAll();organizations.deleteAll();}
 @Test void shouldConnectUserAndAnimal()throws Exception{
  AddressData address=new AddressData("Rua","1",null,"Centro","Canoas","RS","92000-000");
  User u=users.save(new User(new UserRequest("Ana","12345678901","ana@a.com","51999","segredo123","ADOTANTE",address),"hash"));
  Organization o=organizations.save(new Organization(new OrganizationRequest("Abrigo","ONG","51999","ong@a.com","9-18",address)));
  Animal a=animals.save(new Animal(new AnimalRequest("Rex","Cão","SRD","Macho",3,new BigDecimal("12.5"),null,"DISPONIVEL",o.getId()),o));
  mvc.perform(post("/api/v1/adocoes").contentType(MediaType.APPLICATION_JSON).content("""
   {"adoptionDate":"2026-08-29","status":"CONCLUIDA","notes":"Tudo certo","userId":%d,"animalId":%d}
   """.formatted(u.getId(),a.getId()))).andExpect(status().isCreated()).andExpect(jsonPath("$.userId").value(u.getId())).andExpect(jsonPath("$.animalId").value(a.getId()));
 }
}
