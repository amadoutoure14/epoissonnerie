package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.entites.Vendeur;

import java.util.Date;

public record PublicationDTO(
        Long id,
        String titre,
        Vendeur vendeur,
        Categorie categorie,
        Date date
) {
}
