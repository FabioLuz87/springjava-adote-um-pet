package br.edu.unisinos.apirest.adocao;
import br.edu.unisinos.apirest.endereco.DadosEndereco; import br.edu.unisinos.apirest.animal.*; import br.edu.unisinos.apirest.entidade.*; import br.edu.unisinos.apirest.usuario.*; import java.math.BigDecimal; import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.http.MediaType; import org.springframework.test.annotation.DirtiesContext; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc @DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_CLASS) class TesteControladorAdocao{
 @Autowired MockMvc mvc; @Autowired RepositorioAdocao adocoes; @Autowired RepositorioAnimal animals; @Autowired RepositorioUsuario usuarios; @Autowired RepositorioEntidade organizations;
 @BeforeEach void clean(){adocoes.deleteAll();animals.deleteAll();usuarios.deleteAll();organizations.deleteAll();}
 @Test void shouldConnectUsuarioAndAnimal()throws Exception{
  DadosEndereco address=new DadosEndereco("Rua","1",null,"Centro","Canoas","RS","92000-000");
  Usuario u=usuarios.save(new Usuario(new RequisicaoUsuario("Ana","12345678901","ana@a.com","51999","segredo123","ADOTANTE",address),"hash"));
  Entidade o=organizations.save(new Entidade(new RequisicaoEntidade("Abrigo","ONG","51999","ong@a.com","9-18",address)));
  Animal a=animals.save(new Animal(new RequisicaoAnimal("Rex","Cão","SRD","Macho",3,new BigDecimal("12.5"),null,"DISPONIVEL",o.getId()),o));
  mvc.perform(post("/api/v1/adocoes").contentType(MediaType.APPLICATION_JSON).content("""
   {"dataAdocao":"2026-08-29","status":"CONCLUIDA","observacoes":"Tudo certo","idUsuario":%d,"idAnimal":%d}
   """.formatted(u.getId(),a.getId()))).andExpect(status().isCreated()).andExpect(jsonPath("$.idUsuario").value(u.getId())).andExpect(jsonPath("$.idAnimal").value(a.getId()));
 }
}

