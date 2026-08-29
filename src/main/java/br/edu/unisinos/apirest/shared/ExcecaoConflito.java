package br.edu.unisinos.apirest.shared;

public class ExcecaoConflito extends RuntimeException {

    public ExcecaoConflito(String message) {
        super(message);
    }
}
