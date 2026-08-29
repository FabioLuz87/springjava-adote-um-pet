package br.edu.unisinos.apirest.usuario;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/usuarios")
public class ControladorUsuario {
    private final ServicoUsuario service;

    public ControladorUsuario(ServicoUsuario service) {
        this.service = service;
    }

    @GetMapping
    public Page<RespostaUsuario> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public RespostaUsuario findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<RespostaUsuario> create(@Valid @RequestBody RequisicaoUsuario request,
                                                   UriComponentsBuilder uri) {
        RespostaUsuario response = service.create(request);
        return ResponseEntity.created(uri.path("/api/v1/usuarios/{id}")
                .buildAndExpand(response.id())
                .toUri()).body(response);
    }

    @PutMapping("/{id}")
    public RespostaUsuario update(@PathVariable Long id, @Valid @RequestBody RequisicaoUsuario request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

