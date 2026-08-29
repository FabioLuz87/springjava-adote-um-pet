package br.edu.unisinos.apirest.shared;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
