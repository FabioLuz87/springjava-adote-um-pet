package br.edu.unisinos.apirest.usuario;

import br.edu.unisinos.apirest.endereco.Endereco;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String nome;
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;
    @Column(nullable = false, unique = true, length = 150)
    private String email;
    @Column(nullable = false, length = 20)
    private String telefone;
    @Column(nullable = false, length = 255)
    private String hashSenha;
    @Column(nullable = false, length = 30)
    private String perfil;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "id_endereco", nullable = false, unique = true)
    private Endereco endereco;

    protected Usuario() {
    }

    public Usuario(RequisicaoUsuario request, String hashSenha) {
        endereco = new Endereco(request.endereco());
        update(request, hashSenha);
    }

    public void update(RequisicaoUsuario request, String newPasswordHash) {
        nome = request.nome();
        cpf = request.cpf();
        email = request.email().toLowerCase();
        telefone = request.telefone();
        perfil = request.perfil();
        endereco.update(request.endereco());
        if (newPasswordHash != null) {
            hashSenha = newPasswordHash;
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getPerfil() {
        return perfil;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}

