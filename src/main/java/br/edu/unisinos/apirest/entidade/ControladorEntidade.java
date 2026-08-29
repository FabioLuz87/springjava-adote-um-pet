package br.edu.unisinos.apirest.entidade;

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
@RequestMapping("/api/v1/entidades")
public class ControladorEntidade {
    private final ServicoEntidade service;

    public ControladorEntidade(ServicoEntidade service) {
        this.service = service;
    }

    @GetMapping
    public Page<RespostaEntidade> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public RespostaEntidade findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<RespostaEntidade> create(@Valid @RequestBody RequisicaoEntidade request,
                                                    UriComponentsBuilder uriBuilder) {
        RespostaEntidade response = service.create(request);
        return ResponseEntity.created(uriBuilder.path("/api/v1/entidades/{id}")
                .buildAndExpand(response.id())
                .toUri()).body(response);
    }

    @PutMapping("/{id}")
    public RespostaEntidade update(@PathVariable Long id, @Valid @RequestBody RequisicaoEntidade request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

