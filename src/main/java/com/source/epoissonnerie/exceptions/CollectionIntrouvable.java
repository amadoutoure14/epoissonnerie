package com.source.epoissonnerie.exceptions;

public class CollectionIntrouvable extends RuntimeException{
    public CollectionIntrouvable(Long id) {
        super("Collection introuvable " + id);
    }
}
