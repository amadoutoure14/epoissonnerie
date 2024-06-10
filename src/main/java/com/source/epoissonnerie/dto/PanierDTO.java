package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Client;

public record PanierDTO(
        Long id,
        String nom,
        String description,
        double prix_total,
        int quantite,
        Client client
) {

}