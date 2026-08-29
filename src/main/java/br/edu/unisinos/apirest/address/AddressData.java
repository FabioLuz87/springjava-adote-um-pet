package br.edu.unisinos.apirest.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressData(
        @NotBlank @Size(max = 150) String street,
        @NotBlank @Size(max = 20) String number,
        @Size(max = 100) String complement,
        @NotBlank @Size(max = 100) String neighborhood,
        @NotBlank @Size(max = 100) String city,
        @NotBlank @Pattern(regexp = "[A-Za-z]{2}") String state,
        @NotBlank @Pattern(regexp = "\\d{5}-?\\d{3}") String zipCode
) {
    public static AddressData from(Address address) {
        return new AddressData(address.getStreet(), address.getNumber(), address.getComplement(),
                address.getNeighborhood(), address.getCity(), address.getState(), address.getZipCode());
    }
}
