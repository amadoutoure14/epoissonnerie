package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Panier;

import java.util.List;

public record ClientDTO(
        Long id,
        String nom,
        String mdp,
        String adresse,
        int tel,
        List<Panier> paniers
) {
}
