package br.edu.unisinos.apirest.animal;
import br.edu.unisinos.apirest.entidade.Entidade; import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="animais")
public class Animal {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=120) private String nome; @Column(nullable=false,length=50) private String especie;
 @Column(nullable=false,length=80) private String raca; @Column(nullable=false,length=20) private String sexo;
 @Column(nullable=false) private Integer idade; @Column(nullable=false,precision=8,scale=2) private BigDecimal peso;
 @Column(length=500) private String descricao; @Column(nullable=false,length=30) private String status;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="id_entidade",nullable=false) private Entidade entidade;
 protected Animal(){} public Animal(RequisicaoAnimal r,Entidade o){entidade=o;update(r,o);}
 public void update(RequisicaoAnimal r,Entidade o){nome=r.nome();especie=r.especie();raca=r.raca();sexo=r.sexo();idade=r.idade();peso=r.peso();descricao=r.descricao();status=r.status();entidade=o;}
 public Long getId(){return id;} public String getNome(){return nome;} public String getEspecie(){return especie;} public String getRaca(){return raca;}
 public String getSexo(){return sexo;} public Integer getIdade(){return idade;} public BigDecimal getPeso(){return peso;} public String getDescricao(){return descricao;}
 public String getStatus(){return status;} public Entidade getEntidade(){return entidade;}
}


