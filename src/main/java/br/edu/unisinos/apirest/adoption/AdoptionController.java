package br.edu.unisinos.apirest.adoption;
import jakarta.validation.Valid; import org.springframework.data.domain.*; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import org.springframework.web.util.UriComponentsBuilder;
@RestController @RequestMapping("/api/v1/adocoes") public class AdoptionController{
 private final AdoptionService service; public AdoptionController(AdoptionService s){service=s;}
 @GetMapping public Page<AdoptionResponse> findAll(Pageable p){return service.findAll(p);} @GetMapping("/{id}") public AdoptionResponse findById(@PathVariable Long id){return service.findById(id);}
 @PostMapping public ResponseEntity<AdoptionResponse> create(@Valid @RequestBody AdoptionRequest r,UriComponentsBuilder uri){AdoptionResponse a=service.create(r);return ResponseEntity.created(uri.path("/api/v1/adocoes/{id}").buildAndExpand(a.id()).toUri()).body(a);}
 @PutMapping("/{id}") public AdoptionResponse update(@PathVariable Long id,@Valid @RequestBody AdoptionRequest r){return service.update(id,r);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
}
