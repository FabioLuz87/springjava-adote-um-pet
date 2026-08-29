package br.edu.unisinos.apirest.adoption;
import br.edu.unisinos.apirest.animal.Animal; import br.edu.unisinos.apirest.user.User; import jakarta.persistence.*; import java.time.LocalDate;
@Entity @Table(name="adoptions") public class Adoption{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private LocalDate adoptionDate; @Column(nullable=false,length=30) private String status; @Column(length=1000) private String notes;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="user_id",nullable=false) private User user;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="animal_id",nullable=false) private Animal animal;
 protected Adoption(){} public Adoption(AdoptionRequest r,User u,Animal a){update(r,u,a);}
 public void update(AdoptionRequest r,User u,Animal a){adoptionDate=r.adoptionDate();status=r.status();notes=r.notes();user=u;animal=a;}
 public Long getId(){return id;} public LocalDate getAdoptionDate(){return adoptionDate;} public String getStatus(){return status;} public String getNotes(){return notes;}
 public User getUser(){return user;} public Animal getAnimal(){return animal;}
}
