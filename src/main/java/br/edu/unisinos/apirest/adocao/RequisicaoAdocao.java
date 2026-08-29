package br.edu.unisinos.apirest.adocao;
import jakarta.validation.constraints.*; import java.time.LocalDate;
public record RequisicaoAdocao(@NotNull @PastOrPresent LocalDate dataAdocao,@NotBlank @Size(max=30) String status,
 @Size(max=1000) String observacoes,@NotNull @Positive Long idUsuario,@NotNull @Positive Long idAnimal){}

