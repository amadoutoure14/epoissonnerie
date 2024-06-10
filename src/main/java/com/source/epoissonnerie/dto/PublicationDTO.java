package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Categorie;

import java.util.List;

public record PublicationDTO(
        Long id,
        String titre,
        List<Categorie> categories
) {
}
