package br.edu.unisinos.apirest.usuario;

import br.edu.unisinos.apirest.endereco.DadosEndereco;

public record RespostaUsuario(Long id, String nome, String cpf, String email, String telefone, String perfil, DadosEndereco endereco) {
    public static RespostaUsuario from(Usuario value) {
        return new RespostaUsuario(value.getId(), value.getNome(), value.getCpf(), value.getEmail(), value.getTelefone(),
                value.getPerfil(), DadosEndereco.from(value.getEndereco()));
    }
}

