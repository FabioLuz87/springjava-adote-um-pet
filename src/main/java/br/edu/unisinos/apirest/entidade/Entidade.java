package br.edu.unisinos.apirest.entidade;

import br.edu.unisinos.apirest.endereco.Endereco;
import br.edu.unisinos.apirest.endereco.DadosEndereco;
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
@Table(name = "entidades")
public class Entidade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120) private String nome;
    @Column(nullable = false, length = 50) private String tipo;
    @Column(nullable = false, length = 20) private String telefone;
    @Column(nullable = false, unique = true, length = 150) private String email;
    @Column(nullable = false, length = 150) private String horarioAtendimento;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "id_endereco", nullable = false, unique = true)
    private Endereco endereco;

    protected Entidade() {}

    public Entidade(RequisicaoEntidade request) {
        endereco = new Endereco(request.endereco());
        updateFields(request);
    }

    public void update(RequisicaoEntidade request) {
        updateFields(request);
        endereco.update(request.endereco());
    }

    private void updateFields(RequisicaoEntidade request) {
        nome = request.nome(); tipo = request.tipo(); telefone = request.telefone();
        email = request.email().toLowerCase(); horarioAtendimento = request.horarioAtendimento();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getHorarioAtendimento() { return horarioAtendimento; }
    public Endereco getEndereco() { return endereco; }
}

