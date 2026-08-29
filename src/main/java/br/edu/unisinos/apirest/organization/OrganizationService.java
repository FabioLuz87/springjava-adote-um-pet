package br.edu.unisinos.apirest.organization;

import br.edu.unisinos.apirest.shared.ConflictException;
import br.edu.unisinos.apirest.shared.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationService {
    private final OrganizationRepository repository;
    public OrganizationService(OrganizationRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public Page<OrganizationResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(OrganizationResponse::from);
    }
    @Transactional(readOnly = true)
    public OrganizationResponse findById(Long id) { return OrganizationResponse.from(get(id)); }
    @Transactional
    public OrganizationResponse create(OrganizationRequest request) {
        if (repository.existsByEmailIgnoreCase(request.email())) throw new ConflictException("E-mail já cadastrado.");
        return OrganizationResponse.from(repository.save(new Organization(request)));
    }
    @Transactional
    public OrganizationResponse update(Long id, OrganizationRequest request) {
        Organization value = get(id);
        if (repository.existsByEmailIgnoreCaseAndIdNot(request.email(), id)) throw new ConflictException("E-mail já cadastrado.");
        value.update(request);
        return OrganizationResponse.from(value);
    }
    @Transactional
    public void delete(Long id) { repository.delete(get(id)); }
    public Organization get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada."));
    }
}
