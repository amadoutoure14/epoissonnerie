package com.source.epoissonnerie.exceptions;

public class PoissonIntrouvable extends RuntimeException{
    public PoissonIntrouvable(Long id) {
        super("Le poisson n°"+id+" est introuvable!");
    }
}
