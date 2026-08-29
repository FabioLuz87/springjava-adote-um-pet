package br.edu.unisinos.apirest.entidade;

import br.edu.unisinos.apirest.shared.ExcecaoConflito;
import br.edu.unisinos.apirest.shared.ExcecaoRecursoNaoEncontrado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicoEntidade {
    private final RepositorioEntidade repository;

    public ServicoEntidade(RepositorioEntidade repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<RespostaEntidade> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(RespostaEntidade::from);
    }
    @Transactional(readOnly = true)
    public RespostaEntidade findById(Long id) {
        return RespostaEntidade.from(get(id));
    }

    @Transactional
    public RespostaEntidade create(RequisicaoEntidade request) {
        if (repository.existsByEmailIgnoreCase(request.email())) {
            throw new ExcecaoConflito("E-mail já cadastrado.");
        }
        return RespostaEntidade.from(repository.save(new Entidade(request)));
    }

    @Transactional
    public RespostaEntidade update(Long id, RequisicaoEntidade request) {
        Entidade value = get(id);
        if (repository.existsByEmailIgnoreCaseAndIdNot(request.email(), id)) {
            throw new ExcecaoConflito("E-mail já cadastrado.");
        }
        value.update(request);
        return RespostaEntidade.from(value);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(get(id));
    }

    public Entidade get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Entidade não encontrada."));
    }
}

