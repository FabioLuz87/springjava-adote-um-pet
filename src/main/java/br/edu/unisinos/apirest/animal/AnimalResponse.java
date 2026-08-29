package br.edu.unisinos.apirest.animal;
import java.math.BigDecimal;
public record AnimalResponse(Long id,String name,String species,String breed,String sex,Integer age,BigDecimal weight,String description,String status,Long organizationId){
 public static AnimalResponse from(Animal a){return new AnimalResponse(a.getId(),a.getName(),a.getSpecies(),a.getBreed(),a.getSex(),a.getAge(),a.getWeight(),a.getDescription(),a.getStatus(),a.getOrganization().getId());}
}
