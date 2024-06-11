package com.source.epoissonnerie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UtilisateurIntrouvableConseil {
    @ExceptionHandler(UtilisateurIntrouvable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String utilisateurIntrouvable(UtilisateurIntrouvable e) {
        return e.getMessage();
    }
}
