package br.edu.unisinos.apirest.usuario;

import br.edu.unisinos.apirest.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequisicaoUsuario(
        @NotBlank @Size(max = 120) String nome,
        @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}") String cpf,
        @NotBlank @Email @Size(max = 150) String email,
        @NotBlank @Size(max = 20) String telefone,
        @Size(min = 8, max = 100) String senha,
        @NotBlank @Size(max = 30) String perfil,
        @NotNull @Valid DadosEndereco endereco
) {}

