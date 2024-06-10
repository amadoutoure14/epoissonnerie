package com.source.epoissonnerie.exceptions;

public class PublicationIntrouvable extends RuntimeException{
    public PublicationIntrouvable(Long id){
        super("La publication " + id +  " non trouvable !");
    }
}
