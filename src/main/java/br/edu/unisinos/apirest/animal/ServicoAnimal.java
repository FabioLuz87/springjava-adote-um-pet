package br.edu.unisinos.apirest.animal;

import br.edu.unisinos.apirest.entidade.Entidade;
import br.edu.unisinos.apirest.entidade.ServicoEntidade;
import br.edu.unisinos.apirest.shared.ExcecaoRecursoNaoEncontrado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicoAnimal {

    private final RepositorioAnimal repository;
    private final ServicoEntidade entidades;

    public ServicoAnimal(RepositorioAnimal repository, ServicoEntidade entidades) {
        this.repository = repository;
        this.entidades = entidades;
    }

    @Transactional(readOnly = true)
    public Page<RespostaAnimal> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(RespostaAnimal::from);
    }

    @Transactional(readOnly = true)
    public RespostaAnimal findById(Long id) {
        return RespostaAnimal.from(get(id));
    }

    @Transactional
    public RespostaAnimal create(RequisicaoAnimal requisicao) {
        Entidade entidade = entidades.get(requisicao.idEntidade());
        Animal animal = repository.save(new Animal(requisicao, entidade));
        return RespostaAnimal.from(animal);
    }

    @Transactional
    public RespostaAnimal update(Long id, RequisicaoAnimal requisicao) {
        Animal animal = get(id);
        Entidade entidade = entidades.get(requisicao.idEntidade());
        animal.update(requisicao, entidade);
        return RespostaAnimal.from(animal);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(get(id));
    }

    public Animal get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Animal não encontrado."));
    }
}
