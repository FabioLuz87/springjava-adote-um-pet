package br.edu.unisinos.apirest.organization;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/entidades")
public class OrganizationController {
    private final OrganizationService service;
    public OrganizationController(OrganizationService service) { this.service = service; }
    @GetMapping public Page<OrganizationResponse> findAll(Pageable pageable) { return service.findAll(pageable); }
    @GetMapping("/{id}") public OrganizationResponse findById(@PathVariable Long id) { return service.findById(id); }
    @PostMapping public ResponseEntity<OrganizationResponse> create(@Valid @RequestBody OrganizationRequest request,
                                                                    UriComponentsBuilder uriBuilder) {
        OrganizationResponse response = service.create(request);
        return ResponseEntity.created(uriBuilder.path("/api/v1/entidades/{id}").buildAndExpand(response.id()).toUri()).body(response);
    }
    @PutMapping("/{id}") public OrganizationResponse update(@PathVariable Long id, @Valid @RequestBody OrganizationRequest request) {
        return service.update(id, request);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id); return ResponseEntity.noContent().build();
    }
}
