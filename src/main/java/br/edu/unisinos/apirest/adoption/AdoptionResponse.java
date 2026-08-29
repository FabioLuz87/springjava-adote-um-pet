package br.edu.unisinos.apirest.adoption; import java.time.LocalDate;
public record AdoptionResponse(Long id,LocalDate adoptionDate,String status,String notes,Long userId,Long animalId){
 public static AdoptionResponse from(Adoption a){return new AdoptionResponse(a.getId(),a.getAdoptionDate(),a.getStatus(),a.getNotes(),a.getUser().getId(),a.getAnimal().getId());}
}
