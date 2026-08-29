package br.edu.unisinos.apirest.entidade;

import br.edu.unisinos.apirest.endereco.DadosEndereco;

public record RespostaEntidade(Long id, String nome, String tipo, String telefone, String email,
                                   String horarioAtendimento, DadosEndereco endereco) {
    public static RespostaEntidade from(Entidade value) {
        return new RespostaEntidade(value.getId(), value.getNome(), value.getTipo(), value.getTelefone(),
                value.getEmail(), value.getHorarioAtendimento(), DadosEndereco.from(value.getEndereco()));
    }
}

