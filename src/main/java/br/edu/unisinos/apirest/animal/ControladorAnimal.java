package br.edu.unisinos.apirest.animal;

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
@RequestMapping("/api/v1/animais")
public class ControladorAnimal {

    private final ServicoAnimal service;

    public ControladorAnimal(ServicoAnimal service) {
        this.service = service;
    }

    @GetMapping
    public Page<RespostaAnimal> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public RespostaAnimal findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<RespostaAnimal> create(
            @Valid @RequestBody RequisicaoAnimal requisicao,
            UriComponentsBuilder uriBuilder) {
        RespostaAnimal resposta = service.create(requisicao);
        return ResponseEntity.created(uriBuilder.path("/api/v1/animais/{id}")
                .buildAndExpand(resposta.id())
                .toUri()).body(resposta);
    }

    @PutMapping("/{id}")
    public RespostaAnimal update(
            @PathVariable Long id,
            @Valid @RequestBody RequisicaoAnimal requisicao) {
        return service.update(id, requisicao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
