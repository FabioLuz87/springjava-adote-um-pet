package br.edu.unisinos.apirest.user;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByCpf(String cpf); boolean existsByEmailIgnoreCase(String email);
    boolean existsByCpfAndIdNot(String cpf, Long id); boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}
