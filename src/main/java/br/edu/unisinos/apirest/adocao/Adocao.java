package br.edu.unisinos.apirest.adocao;
import br.edu.unisinos.apirest.animal.Animal; import br.edu.unisinos.apirest.usuario.Usuario; import jakarta.persistence.*; import java.time.LocalDate;
@Entity @Table(name="adocoes") public class Adocao{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private LocalDate dataAdocao; @Column(nullable=false,length=30) private String status; @Column(length=1000) private String observacoes;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="id_usuario",nullable=false) private Usuario usuario;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="id_animal",nullable=false) private Animal animal;
 protected Adocao(){} public Adocao(RequisicaoAdocao r,Usuario u,Animal a){update(r,u,a);}
 public void update(RequisicaoAdocao r,Usuario u,Animal a){dataAdocao=r.dataAdocao();status=r.status();observacoes=r.observacoes();usuario=u;animal=a;}
 public Long getId(){return id;} public LocalDate getAdocaoDate(){return dataAdocao;} public String getStatus(){return status;} public String getObservacoes(){return observacoes;}
 public Usuario getUsuario(){return usuario;} public Animal getAnimal(){return animal;}
}

