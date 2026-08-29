package br.edu.unisinos.apirest.usuario;

import br.edu.unisinos.apirest.shared.ExcecaoConflito;
import br.edu.unisinos.apirest.shared.ExcecaoRecursoNaoEncontrado;
import br.edu.unisinos.apirest.shared.GeradorHashSenha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicoUsuario {
    private final RepositorioUsuario repository; private final GeradorHashSenha hashSenhaer;
    public ServicoUsuario(RepositorioUsuario repository, GeradorHashSenha hashSenhaer) { this.repository = repository; this.hashSenhaer = hashSenhaer; }
    @Transactional(readOnly = true) public Page<RespostaUsuario> findAll(Pageable pageable) { return repository.findAll(pageable).map(RespostaUsuario::from); }
    @Transactional(readOnly = true) public RespostaUsuario findById(Long id) { return RespostaUsuario.from(get(id)); }
    @Transactional public RespostaUsuario create(RequisicaoUsuario request) {
        validateUnique(request, null);
        if (request.senha() == null || request.senha().isBlank()) throw new IllegalArgumentException("Senha é obrigatória.");
        return RespostaUsuario.from(repository.save(new Usuario(request, hashSenhaer.hash(request.senha()))));
    }
    @Transactional public RespostaUsuario update(Long id, RequisicaoUsuario request) {
        Usuario value = get(id); validateUnique(request, id);
        String hash = request.senha() == null || request.senha().isBlank() ? null : hashSenhaer.hash(request.senha());
        value.update(request, hash); return RespostaUsuario.from(value);
    }
    @Transactional public void delete(Long id) { repository.delete(get(id)); }
    public Usuario get(Long id) { return repository.findById(id).orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Usuário não encontrado.")); }
    private void validateUnique(RequisicaoUsuario request, Long id) {
        boolean cpfExists = id == null ? repository.existsByCpf(request.cpf()) : repository.existsByCpfAndIdNot(request.cpf(), id);
        boolean emailExists = id == null ? repository.existsByEmailIgnoreCase(request.email()) : repository.existsByEmailIgnoreCaseAndIdNot(request.email(), id);
        if (cpfExists) throw new ExcecaoConflito("CPF já cadastrado."); if (emailExists) throw new ExcecaoConflito("E-mail já cadastrado.");
    }
}

