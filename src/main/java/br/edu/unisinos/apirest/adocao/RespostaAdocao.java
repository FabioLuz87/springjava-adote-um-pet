package br.edu.unisinos.apirest.adocao; import java.time.LocalDate;
public record RespostaAdocao(Long id,LocalDate dataAdocao,String status,String observacoes,Long idUsuario,Long idAnimal){
 public static RespostaAdocao from(Adocao a){return new RespostaAdocao(a.getId(),a.getAdocaoDate(),a.getStatus(),a.getObservacoes(),a.getUsuario().getId(),a.getAnimal().getId());}
}

