package br.edu.unisinos.apirest.entidade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEntidade extends JpaRepository<Entidade, Long> {
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}

