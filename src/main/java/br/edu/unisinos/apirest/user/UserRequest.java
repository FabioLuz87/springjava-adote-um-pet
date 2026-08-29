package br.edu.unisinos.apirest.user;

import br.edu.unisinos.apirest.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record UserRequest(
        @NotBlank @Size(max = 120) String name,
        @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}") String cpf,
        @NotBlank @Email @Size(max = 150) String email,
        @NotBlank @Size(max = 20) String phone,
        @Size(min = 8, max = 100) String password,
        @NotBlank @Size(max = 30) String profile,
        @NotNull @Valid AddressData address
) {}
