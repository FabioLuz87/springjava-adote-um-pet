package br.edu.unisinos.apirest.animal;
import br.edu.unisinos.apirest.organization.*; import br.edu.unisinos.apirest.shared.ResourceNotFoundException; import org.springframework.data.domain.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service public class AnimalService{
 private final AnimalRepository repository; private final OrganizationService organizations;
 public AnimalService(AnimalRepository r,OrganizationService o){repository=r;organizations=o;}
 @Transactional(readOnly=true) public Page<AnimalResponse> findAll(Pageable p){return repository.findAll(p).map(AnimalResponse::from);}
 @Transactional(readOnly=true) public AnimalResponse findById(Long id){return AnimalResponse.from(get(id));}
 @Transactional public AnimalResponse create(AnimalRequest r){return AnimalResponse.from(repository.save(new Animal(r,organizations.get(r.organizationId()))));}
 @Transactional public AnimalResponse update(Long id,AnimalRequest r){Animal a=get(id);a.update(r,organizations.get(r.organizationId()));return AnimalResponse.from(a);}
 @Transactional public void delete(Long id){repository.delete(get(id));}
 public Animal get(Long id){return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Animal não encontrado."));}
}
