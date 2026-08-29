package br.edu.unisinos.apirest.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String street;
    @Column(nullable = false, length = 20)
    private String number;
    @Column(length = 100)
    private String complement;
    @Column(nullable = false, length = 100)
    private String neighborhood;
    @Column(nullable = false, length = 100)
    private String city;
    @Column(nullable = false, length = 2)
    private String state;
    @Column(nullable = false, length = 9)
    private String zipCode;

    protected Address() {}

    public Address(AddressData data) { update(data); }

    public void update(AddressData data) {
        street = data.street(); number = data.number(); complement = data.complement();
        neighborhood = data.neighborhood(); city = data.city(); state = data.state().toUpperCase();
        zipCode = data.zipCode();
    }

    public Long getId() { return id; }
    public String getStreet() { return street; }
    public String getNumber() { return number; }
    public String getComplement() { return complement; }
    public String getNeighborhood() { return neighborhood; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
}
