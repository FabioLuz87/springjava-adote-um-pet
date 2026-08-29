package br.edu.unisinos.apirest.user;

import br.edu.unisinos.apirest.address.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 120) private String name;
    @Column(nullable = false, unique = true, length = 14) private String cpf;
    @Column(nullable = false, unique = true, length = 150) private String email;
    @Column(nullable = false, length = 20) private String phone;
    @Column(nullable = false, length = 255) private String passwordHash;
    @Column(nullable = false, length = 30) private String profile;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "address_id", nullable = false, unique = true) private Address address;

    protected User() {}
    public User(UserRequest request, String passwordHash) {
        address = new Address(request.address()); update(request, passwordHash);
    }
    public void update(UserRequest request, String newPasswordHash) {
        name = request.name(); cpf = request.cpf(); email = request.email().toLowerCase(); phone = request.phone();
        profile = request.profile(); address.update(request.address());
        if (newPasswordHash != null) passwordHash = newPasswordHash;
    }
    public Long getId() { return id; } public String getName() { return name; } public String getCpf() { return cpf; }
    public String getEmail() { return email; } public String getPhone() { return phone; }
    public String getProfile() { return profile; } public Address getAddress() { return address; }
}
