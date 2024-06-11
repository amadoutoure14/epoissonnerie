package com.source.epoissonnerie.exceptions;

public class CommandeIntrouvable extends RuntimeException {
    public CommandeIntrouvable(Long id){
       super("La commande n°"+id+" est introuvable!");
    }
}
