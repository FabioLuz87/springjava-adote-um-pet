package br.edu.unisinos.apirest.animal;
import jakarta.validation.Valid; import org.springframework.data.domain.*; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import org.springframework.web.util.UriComponentsBuilder;
@RestController @RequestMapping("/api/v1/animais") public class AnimalController{
 private final AnimalService service; public AnimalController(AnimalService s){service=s;}
 @GetMapping public Page<AnimalResponse> findAll(Pageable p){return service.findAll(p);} @GetMapping("/{id}") public AnimalResponse findById(@PathVariable Long id){return service.findById(id);}
 @PostMapping public ResponseEntity<AnimalResponse> create(@Valid @RequestBody AnimalRequest r,UriComponentsBuilder uri){AnimalResponse a=service.create(r);return ResponseEntity.created(uri.path("/api/v1/animais/{id}").buildAndExpand(a.id()).toUri()).body(a);}
 @PutMapping("/{id}") public AnimalResponse update(@PathVariable Long id,@Valid @RequestBody AnimalRequest r){return service.update(id,r);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
}
