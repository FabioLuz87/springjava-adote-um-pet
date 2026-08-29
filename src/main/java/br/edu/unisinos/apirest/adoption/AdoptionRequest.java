package br.edu.unisinos.apirest.adoption;
import jakarta.validation.constraints.*; import java.time.LocalDate;
public record AdoptionRequest(@NotNull @PastOrPresent LocalDate adoptionDate,@NotBlank @Size(max=30) String status,
 @Size(max=1000) String notes,@NotNull @Positive Long userId,@NotNull @Positive Long animalId){}
