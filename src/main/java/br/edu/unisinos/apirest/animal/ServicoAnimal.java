package br.edu.unisinos.apirest.animal;
import br.edu.unisinos.apirest.entidade.*; import br.edu.unisinos.apirest.shared.ExcecaoRecursoNaoEncontrado; import org.springframework.data.domain.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service public class ServicoAnimal{
 private final RepositorioAnimal repository; private final ServicoEntidade entidades;
 public ServicoAnimal(RepositorioAnimal r,ServicoEntidade o){repository=r;entidades=o;}
 @Transactional(readOnly=true) public Page<RespostaAnimal> findAll(Pageable p){return repository.findAll(p).map(RespostaAnimal::from);}
 @Transactional(readOnly=true) public RespostaAnimal findById(Long id){return RespostaAnimal.from(get(id));}
 @Transactional public RespostaAnimal create(RequisicaoAnimal r){return RespostaAnimal.from(repository.save(new Animal(r,entidades.get(r.idEntidade()))));}
 @Transactional public RespostaAnimal update(Long id,RequisicaoAnimal r){Animal a=get(id);a.update(r,entidades.get(r.idEntidade()));return RespostaAnimal.from(a);}
 @Transactional public void delete(Long id){repository.delete(get(id));}
 public Animal get(Long id){return repository.findById(id).orElseThrow(()->new ExcecaoRecursoNaoEncontrado("Animal não encontrado."));}
}


