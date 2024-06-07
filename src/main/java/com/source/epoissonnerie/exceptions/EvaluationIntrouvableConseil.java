package com.source.epoissonnerie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EvaluationIntrouvableConseil {
    @ExceptionHandler(EvaluationIntrouvable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEvaluationIntrouvable(EvaluationIntrouvable e) {
        return e.getMessage();
    }
}