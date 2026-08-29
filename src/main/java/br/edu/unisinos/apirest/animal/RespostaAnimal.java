package br.edu.unisinos.apirest.animal;
import java.math.BigDecimal;
public record RespostaAnimal(Long id,String nome,String especie,String raca,String sexo,Integer idade,BigDecimal peso,String descricao,String status,Long idEntidade){
 public static RespostaAnimal from(Animal a){return new RespostaAnimal(a.getId(),a.getNome(),a.getEspecie(),a.getRaca(),a.getSexo(),a.getIdade(),a.getPeso(),a.getDescricao(),a.getStatus(),a.getEntidade().getId());}
}


