package com.source.epoissonnerie.exceptions;

public class PanierIntrouvable extends RuntimeException{
    public PanierIntrouvable(Long id) {
        super("Le panier n°"+id + "introuvable !");
    }
}
