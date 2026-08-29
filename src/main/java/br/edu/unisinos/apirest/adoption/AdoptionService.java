package br.edu.unisinos.apirest.adoption;
import br.edu.unisinos.apirest.animal.AnimalService; import br.edu.unisinos.apirest.shared.ResourceNotFoundException; import br.edu.unisinos.apirest.user.UserService; import org.springframework.data.domain.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service public class AdoptionService{
 private final AdoptionRepository repository; private final UserService users; private final AnimalService animals;
 public AdoptionService(AdoptionRepository r,UserService u,AnimalService a){repository=r;users=u;animals=a;}
 @Transactional(readOnly=true) public Page<AdoptionResponse> findAll(Pageable p){return repository.findAll(p).map(AdoptionResponse::from);}
 @Transactional(readOnly=true) public AdoptionResponse findById(Long id){return AdoptionResponse.from(get(id));}
 @Transactional public AdoptionResponse create(AdoptionRequest r){return AdoptionResponse.from(repository.save(new Adoption(r,users.get(r.userId()),animals.get(r.animalId()))));}
 @Transactional public AdoptionResponse update(Long id,AdoptionRequest r){Adoption a=get(id);a.update(r,users.get(r.userId()),animals.get(r.animalId()));return AdoptionResponse.from(a);}
 @Transactional public void delete(Long id){repository.delete(get(id));}
 private Adoption get(Long id){return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Adoção não encontrada."));}
}
