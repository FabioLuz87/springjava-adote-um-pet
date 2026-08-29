package br.edu.unisinos.apirest.adocao;
import br.edu.unisinos.apirest.animal.ServicoAnimal; import br.edu.unisinos.apirest.shared.ExcecaoRecursoNaoEncontrado; import br.edu.unisinos.apirest.usuario.ServicoUsuario; import org.springframework.data.domain.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service public class ServicoAdocao{
 private final RepositorioAdocao repository; private final ServicoUsuario usuarios; private final ServicoAnimal animals;
 public ServicoAdocao(RepositorioAdocao r,ServicoUsuario u,ServicoAnimal a){repository=r;usuarios=u;animals=a;}
 @Transactional(readOnly=true) public Page<RespostaAdocao> findAll(Pageable p){return repository.findAll(p).map(RespostaAdocao::from);}
 @Transactional(readOnly=true) public RespostaAdocao findById(Long id){return RespostaAdocao.from(get(id));}
 @Transactional public RespostaAdocao create(RequisicaoAdocao r){return RespostaAdocao.from(repository.save(new Adocao(r,usuarios.get(r.idUsuario()),animals.get(r.idAnimal()))));}
 @Transactional public RespostaAdocao update(Long id,RequisicaoAdocao r){Adocao a=get(id);a.update(r,usuarios.get(r.idUsuario()),animals.get(r.idAnimal()));return RespostaAdocao.from(a);}
 @Transactional public void delete(Long id){repository.delete(get(id));}
 private Adocao get(Long id){return repository.findById(id).orElseThrow(()->new ExcecaoRecursoNaoEncontrado("Adoção não encontrada."));}
}

