package com.source.epoissonnerie.exceptions;

public class AchatIntrouvable extends RuntimeException{
    public AchatIntrouvable(Long id) {
        super("L'achat n°"+id+" est introuvable!");
    }
}
