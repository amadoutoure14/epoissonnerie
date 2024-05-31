package com.source.epoissonnerie.exceptions;

public class CommentaireIntrouvable extends RuntimeException{
    public CommentaireIntrouvable(Long id) {
        super("Le commentaire n°" + id + " est introuvable!");
    }
}
