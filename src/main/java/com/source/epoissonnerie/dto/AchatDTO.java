package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Commande;

import java.util.Date;

public record AchatDTO(
        Long id,
        double prix,
        double montant,
        int quantite,
        Date date,
        Commande commande
) {
}
