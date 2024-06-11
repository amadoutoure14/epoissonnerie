package com.source.epoissonnerie.exceptions;

public class VendeurIntrouvable extends RuntimeException{
    public VendeurIntrouvable(Long id) {
        super("Aucun vendeur pour l'ID: " + id);
    }
}
