package br.edu.unisinos.apirest.animal;
import jakarta.validation.constraints.*; import java.math.BigDecimal;
public record AnimalRequest(@NotBlank @Size(max=120) String name,@NotBlank @Size(max=50) String species,
 @NotBlank @Size(max=80) String breed,@NotBlank @Size(max=20) String sex,@NotNull @PositiveOrZero Integer age,
 @NotNull @DecimalMin(value="0.0",inclusive=false) BigDecimal weight,@Size(max=500) String description,
 @NotBlank @Size(max=30) String status,@NotNull @Positive Long organizationId){}
