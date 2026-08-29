package br.edu.unisinos.apirest.organization;

import br.edu.unisinos.apirest.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrganizationRequest(
        @NotBlank @Size(max = 120) String name,
        @NotBlank @Size(max = 50) String type,
        @NotBlank @Size(max = 20) String phone,
        @NotBlank @Email @Size(max = 150) String email,
        @NotBlank @Size(max = 150) String serviceHours,
        @NotNull @Valid AddressData address
) {}
