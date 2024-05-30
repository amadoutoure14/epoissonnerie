package com.source.epoissonnerie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VendeurIntrouvableConseil {
    @ExceptionHandler(VendeurIntrouvable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String VendeurIntrouvableException(VendeurIntrouvable ex){
        return ex.getMessage();
    }
}