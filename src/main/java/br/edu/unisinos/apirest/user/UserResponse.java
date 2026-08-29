package br.edu.unisinos.apirest.user;

import br.edu.unisinos.apirest.address.AddressData;

public record UserResponse(Long id, String name, String cpf, String email, String phone, String profile, AddressData address) {
    public static UserResponse from(User value) {
        return new UserResponse(value.getId(), value.getName(), value.getCpf(), value.getEmail(), value.getPhone(),
                value.getProfile(), AddressData.from(value.getAddress()));
    }
}
