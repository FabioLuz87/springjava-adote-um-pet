package br.edu.unisinos.apirest.entidade;

import br.edu.unisinos.apirest.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequisicaoEntidade(
        @NotBlank @Size(max = 120) String nome,
        @NotBlank @Size(max = 50) String tipo,
        @NotBlank @Size(max = 20) String telefone,
        @NotBlank @Email @Size(max = 150) String email,
        @NotBlank @Size(max = 150) String horarioAtendimento,
        @NotNull @Valid DadosEndereco endereco
) {
}

