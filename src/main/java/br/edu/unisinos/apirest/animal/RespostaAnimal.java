package br.edu.unisinos.apirest.animal;

import java.math.BigDecimal;

public record RespostaAnimal(
        Long id,
        String nome,
        String especie,
        String raca,
        String sexo,
        Integer idade,
        BigDecimal peso,
        String descricao,
        String status,
        Long idEntidade) {

    public static RespostaAnimal from(Animal animal) {
        return new RespostaAnimal(
                animal.getId(),
                animal.getNome(),
                animal.getEspecie(),
                animal.getRaca(),
                animal.getSexo(),
                animal.getIdade(),
                animal.getPeso(),
                animal.getDescricao(),
                animal.getStatus(),
                animal.getEntidade().getId());
    }
}
