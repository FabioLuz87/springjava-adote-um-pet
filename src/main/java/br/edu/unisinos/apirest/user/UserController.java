package br.edu.unisinos.apirest.user;
import jakarta.validation.Valid;
import org.springframework.data.domain.*; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import org.springframework.web.util.UriComponentsBuilder;
@RestController @RequestMapping("/api/v1/usuarios")
public class UserController {
    private final UserService service; public UserController(UserService service) { this.service = service; }
    @GetMapping public Page<UserResponse> findAll(Pageable pageable) { return service.findAll(pageable); }
    @GetMapping("/{id}") public UserResponse findById(@PathVariable Long id) { return service.findById(id); }
    @PostMapping public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request, UriComponentsBuilder uri) {
        UserResponse response = service.create(request); return ResponseEntity.created(uri.path("/api/v1/usuarios/{id}").buildAndExpand(response.id()).toUri()).body(response);
    }
    @PutMapping("/{id}") public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserRequest request) { return service.update(id, request); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
