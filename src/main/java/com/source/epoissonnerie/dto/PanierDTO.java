package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Utilisateur;
import jakarta.validation.constraints.NotNull;

public record PanierDTO(
        Long id,
        String nom,
        String description,

        double prix_total,
        int quantite,
        Utilisateur utilisateur
) {

}