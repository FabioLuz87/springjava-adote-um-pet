package br.edu.unisinos.apirest.animal;
import br.edu.unisinos.apirest.organization.Organization; import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="animals")
public class Animal {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=120) private String name; @Column(nullable=false,length=50) private String species;
 @Column(nullable=false,length=80) private String breed; @Column(nullable=false,length=20) private String sex;
 @Column(nullable=false) private Integer age; @Column(nullable=false,precision=8,scale=2) private BigDecimal weight;
 @Column(length=500) private String description; @Column(nullable=false,length=30) private String status;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="organization_id",nullable=false) private Organization organization;
 protected Animal(){} public Animal(AnimalRequest r,Organization o){organization=o;update(r,o);}
 public void update(AnimalRequest r,Organization o){name=r.name();species=r.species();breed=r.breed();sex=r.sex();age=r.age();weight=r.weight();description=r.description();status=r.status();organization=o;}
 public Long getId(){return id;} public String getName(){return name;} public String getSpecies(){return species;} public String getBreed(){return breed;}
 public String getSex(){return sex;} public Integer getAge(){return age;} public BigDecimal getWeight(){return weight;} public String getDescription(){return description;}
 public String getStatus(){return status;} public Organization getOrganization(){return organization;}
}
