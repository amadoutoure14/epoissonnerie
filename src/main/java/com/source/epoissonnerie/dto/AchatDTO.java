package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Panier;

public record AchatDTO(
        Long id,
        double prix,
        double montant,
        int quantite,
        Panier panier
) {
}
