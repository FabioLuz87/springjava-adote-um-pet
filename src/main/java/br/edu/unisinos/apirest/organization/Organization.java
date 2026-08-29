package br.edu.unisinos.apirest.organization;

import br.edu.unisinos.apirest.address.Address;
import br.edu.unisinos.apirest.address.AddressData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "organizations")
public class Organization {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120) private String name;
    @Column(nullable = false, length = 50) private String type;
    @Column(nullable = false, length = 20) private String phone;
    @Column(nullable = false, unique = true, length = 150) private String email;
    @Column(nullable = false, length = 150) private String serviceHours;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private Address address;

    protected Organization() {}

    public Organization(OrganizationRequest request) {
        address = new Address(request.address());
        updateFields(request);
    }

    public void update(OrganizationRequest request) {
        updateFields(request);
        address.update(request.address());
    }

    private void updateFields(OrganizationRequest request) {
        name = request.name(); type = request.type(); phone = request.phone();
        email = request.email().toLowerCase(); serviceHours = request.serviceHours();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getServiceHours() { return serviceHours; }
    public Address getAddress() { return address; }
}
