package br.edu.unisinos.apirest.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ManipuladorExcecoesApi {

    @ExceptionHandler(ExcecaoRecursoNaoEncontrado.class)
    ProblemDetail handleNotFound(ExcecaoRecursoNaoEncontrado exception) {
        return problem(HttpStatus.NOT_FOUND, "Recurso não encontrado", exception.getMessage());
    }

    @ExceptionHandler(ExcecaoConflito.class)
    ProblemDetail handleConflict(ExcecaoConflito exception) {
        return problem(HttpStatus.CONFLICT, "Conflito", exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleValidation(MethodArgumentNotValidException exception) {
        ProblemDetail detail = problem(
                HttpStatus.BAD_REQUEST,
                "Dados inválidos",
                "Um ou mais campos não passaram pela validação."
        );
        Map<String, String> fields = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage() == null ? "valor inválido" : error.getDefaultMessage(),
                        (first, ignored) -> first
                ));
        detail.setProperty("fields", fields);
        return detail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ProblemDetail handleIllegalArgument(IllegalArgumentException exception) {
        return problem(HttpStatus.BAD_REQUEST, "Dados inválidos", exception.getMessage());
    }

    private ProblemDetail problem(HttpStatus status, String title, String detail) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        return problem;
    }
}
