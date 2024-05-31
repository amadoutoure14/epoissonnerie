package com.source.epoissonnerie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentaireIntrouvableConseil {
    @ExceptionHandler(CommentaireIntrouvable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String CommentaireIntrouvable(CommentaireIntrouvable e) {
        return e.getMessage();
    }
}
