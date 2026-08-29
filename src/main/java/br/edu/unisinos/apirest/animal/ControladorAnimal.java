package br.edu.unisinos.apirest.animal;
import jakarta.validation.Valid; import org.springframework.data.domain.*; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import org.springframework.web.util.UriComponentsBuilder;
@RestController @RequestMapping("/api/v1/animais") public class ControladorAnimal{
 private final ServicoAnimal service; public ControladorAnimal(ServicoAnimal s){service=s;}
 @GetMapping public Page<RespostaAnimal> findAll(Pageable p){return service.findAll(p);} @GetMapping("/{id}") public RespostaAnimal findById(@PathVariable Long id){return service.findById(id);}
 @PostMapping public ResponseEntity<RespostaAnimal> create(@Valid @RequestBody RequisicaoAnimal r,UriComponentsBuilder uri){RespostaAnimal a=service.create(r);return ResponseEntity.created(uri.path("/api/v1/animais/{id}").buildAndExpand(a.id()).toUri()).body(a);}
 @PutMapping("/{id}") public RespostaAnimal update(@PathVariable Long id,@Valid @RequestBody RequisicaoAnimal r){return service.update(id,r);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
}


