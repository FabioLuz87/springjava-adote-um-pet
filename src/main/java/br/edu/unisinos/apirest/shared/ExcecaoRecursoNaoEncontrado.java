package br.edu.unisinos.apirest.shared;

public class ExcecaoRecursoNaoEncontrado extends RuntimeException {

    public ExcecaoRecursoNaoEncontrado(String message) {
        super(message);
    }
}
