package br.edu.unisinos.apirest.user;

import br.edu.unisinos.apirest.shared.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository repository; private final PasswordHasher passwordHasher;
    public UserService(UserRepository repository, PasswordHasher passwordHasher) { this.repository = repository; this.passwordHasher = passwordHasher; }
    @Transactional(readOnly = true) public Page<UserResponse> findAll(Pageable pageable) { return repository.findAll(pageable).map(UserResponse::from); }
    @Transactional(readOnly = true) public UserResponse findById(Long id) { return UserResponse.from(get(id)); }
    @Transactional public UserResponse create(UserRequest request) {
        validateUnique(request, null);
        if (request.password() == null || request.password().isBlank()) throw new IllegalArgumentException("Senha é obrigatória.");
        return UserResponse.from(repository.save(new User(request, passwordHasher.hash(request.password()))));
    }
    @Transactional public UserResponse update(Long id, UserRequest request) {
        User value = get(id); validateUnique(request, id);
        String hash = request.password() == null || request.password().isBlank() ? null : passwordHasher.hash(request.password());
        value.update(request, hash); return UserResponse.from(value);
    }
    @Transactional public void delete(Long id) { repository.delete(get(id)); }
    public User get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado.")); }
    private void validateUnique(UserRequest request, Long id) {
        boolean cpfExists = id == null ? repository.existsByCpf(request.cpf()) : repository.existsByCpfAndIdNot(request.cpf(), id);
        boolean emailExists = id == null ? repository.existsByEmailIgnoreCase(request.email()) : repository.existsByEmailIgnoreCaseAndIdNot(request.email(), id);
        if (cpfExists) throw new ConflictException("CPF já cadastrado."); if (emailExists) throw new ConflictException("E-mail já cadastrado.");
    }
}
