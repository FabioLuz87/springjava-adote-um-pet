package br.edu.unisinos.apirest.animal;
import br.edu.unisinos.apirest.endereco.DadosEndereco; import br.edu.unisinos.apirest.entidade.*; import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.http.MediaType; import org.springframework.test.annotation.DirtiesContext; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc @DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_CLASS) class TesteControladorAnimal{
 @Autowired MockMvc mvc; @Autowired RepositorioEntidade entidades; @Autowired RepositorioAnimal animais;
 @BeforeEach void setup(){animais.deleteAll();entidades.deleteAll();}
 @Test void shouldAssociateAnimalWithEntidade()throws Exception{
  Entidade o=entidades.save(new Entidade(new RequisicaoEntidade("Abrigo","ONG","51999","a@b.com","9-18",new DadosEndereco("Rua","1",null,"Centro","Canoas","RS","92000-000"))));
  mvc.perform(post("/api/v1/animais").contentType(MediaType.APPLICATION_JSON).content("""
   {"nome":"Rex","especie":"Cão","raca":"SRD","sexo":"Macho","idade":3,"peso":12.5,"descricao":"Dócil","status":"DISPONIVEL","idEntidade":%d}
   """.formatted(o.getId()))).andExpect(status().isCreated()).andExpect(jsonPath("$.idEntidade").value(o.getId()));
 }
}


