package com.source.epoissonnerie.exceptions;

public class EvaluationIntrouvable extends RuntimeException{
    public EvaluationIntrouvable(Long id) {
        super("L'evaluation n°" +id+" est introuvable !" );
    }
}
