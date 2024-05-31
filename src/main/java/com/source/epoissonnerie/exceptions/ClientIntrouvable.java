package com.source.epoissonnerie.exceptions;

public class ClientIntrouvable extends RuntimeException{
    public ClientIntrouvable(Long id) {
        super("Le client n°" + id + " introuvable!");
    }
}
