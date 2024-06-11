package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Client;
import com.source.epoissonnerie.entites.Commande;

import java.util.List;

public record PanierDTO(
        Long id,
        String nom,
        String description,
        double prix_total,
        int quantite,
        Client client,
        List<Commande> commandes
) {

}