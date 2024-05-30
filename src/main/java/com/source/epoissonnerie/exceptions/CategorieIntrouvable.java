package com.source.epoissonnerie.exceptions;

public class CategorieIntrouvable extends RuntimeException{
    public CategorieIntrouvable(Long id) {
        super("La collection N°" + id+" est introuvable!");
    }
}
