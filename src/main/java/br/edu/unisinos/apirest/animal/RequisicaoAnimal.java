package br.edu.unisinos.apirest.animal;
import jakarta.validation.constraints.*; import java.math.BigDecimal;
public record RequisicaoAnimal(@NotBlank @Size(max=120) String nome,@NotBlank @Size(max=50) String especie,
 @NotBlank @Size(max=80) String raca,@NotBlank @Size(max=20) String sexo,@NotNull @PositiveOrZero Integer idade,
 @NotNull @DecimalMin(value="0.0",inclusive=false) BigDecimal peso,@Size(max=500) String descricao,
 @NotBlank @Size(max=30) String status,@NotNull @Positive Long idEntidade){}


