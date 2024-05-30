package com.source.epoissonnerie.exceptions;

public class VendeurIntrouvable extends RuntimeException{
    public VendeurIntrouvable(Long id) {
        super("Vendeur non trouver" + id);
    }
}
