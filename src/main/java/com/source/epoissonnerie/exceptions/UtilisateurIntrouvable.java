package com.source.epoissonnerie.exceptions;

public class UtilisateurIntrouvable extends RuntimeException{
    public UtilisateurIntrouvable(Long id) {
        super("L'utilisateur n°"+id+" est introuvable");
    }
}
