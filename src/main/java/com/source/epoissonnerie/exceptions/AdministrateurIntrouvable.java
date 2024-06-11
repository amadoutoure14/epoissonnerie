package com.source.epoissonnerie.exceptions;

public class AdministrateurIntrouvable extends RuntimeException{
    public AdministrateurIntrouvable(Long id) {
        super("L'administrateur n°"+id+" est introuvable!");
    }
}
