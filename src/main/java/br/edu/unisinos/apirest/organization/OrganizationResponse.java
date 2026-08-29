package br.edu.unisinos.apirest.organization;

import br.edu.unisinos.apirest.address.AddressData;

public record OrganizationResponse(Long id, String name, String type, String phone, String email,
                                   String serviceHours, AddressData address) {
    public static OrganizationResponse from(Organization value) {
        return new OrganizationResponse(value.getId(), value.getName(), value.getType(), value.getPhone(),
                value.getEmail(), value.getServiceHours(), AddressData.from(value.getAddress()));
    }
}
