package br.edu.unisinos.apirest.endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String logradouro;
    @Column(nullable = false, length = 20)
    private String numero;
    @Column(length = 100)
    private String complemento;
    @Column(nullable = false, length = 100)
    private String bairro;
    @Column(nullable = false, length = 100)
    private String cidade;
    @Column(nullable = false, length = 2)
    private String estado;
    @Column(nullable = false, length = 9)
    private String cep;

    protected Endereco() {
    }

    public Endereco(DadosEndereco data) {
        update(data);
    }

    public void update(DadosEndereco data) {
        logradouro = data.logradouro();
        numero = data.numero();
        complemento = data.complemento();
        bairro = data.bairro();
        cidade = data.cidade();
        estado = data.estado().toUpperCase();
        cep = data.cep();
    }

    public Long getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }
}

