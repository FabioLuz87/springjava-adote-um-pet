package br.edu.unisinos.apirest.animal;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record RequisicaoAnimal(
        @NotBlank @Size(max = 120) String nome,
        @NotBlank @Size(max = 50) String especie,
        @NotBlank @Size(max = 80) String raca,
        @NotBlank @Size(max = 20) String sexo,
        @NotNull @PositiveOrZero Integer idade,
        @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal peso,
        @Size(max = 500) String descricao,
        @NotBlank @Size(max = 30) String status,
        @NotNull @Positive Long idEntidade) {
}
