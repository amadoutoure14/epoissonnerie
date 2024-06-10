package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Poisson;

import java.util.Date;

public record CommentaireDTO(
        Long id,String contenu,
        Date date,
        Poisson poisson
) {
}
