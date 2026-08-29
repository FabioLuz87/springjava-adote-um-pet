package br.edu.unisinos.apirest.adocao;
import jakarta.validation.Valid; import org.springframework.data.domain.*; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import org.springframework.web.util.UriComponentsBuilder;
@RestController @RequestMapping("/api/v1/adocoes") public class ControladorAdocao{
 private final ServicoAdocao service; public ControladorAdocao(ServicoAdocao s){service=s;}
 @GetMapping public Page<RespostaAdocao> findAll(Pageable p){return service.findAll(p);} @GetMapping("/{id}") public RespostaAdocao findById(@PathVariable Long id){return service.findById(id);}
 @PostMapping public ResponseEntity<RespostaAdocao> create(@Valid @RequestBody RequisicaoAdocao r,UriComponentsBuilder uri){RespostaAdocao a=service.create(r);return ResponseEntity.created(uri.path("/api/v1/adocoes/{id}").buildAndExpand(a.id()).toUri()).body(a);}
 @PutMapping("/{id}") public RespostaAdocao update(@PathVariable Long id,@Valid @RequestBody RequisicaoAdocao r){return service.update(id,r);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
}

