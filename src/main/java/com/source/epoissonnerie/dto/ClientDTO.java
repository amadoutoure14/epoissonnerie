package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Commentaire;
import com.source.epoissonnerie.entites.Evaluation;
import com.source.epoissonnerie.entites.Panier;

import java.util.Date;
import java.util.List;

public record ClientDTO(
        Long id,
        String nom,
        String mdp,
        String adresse,
        int tel,
        Date date,
        List<Panier> paniers,
        Commentaire commentaire,
        Evaluation evaluation
) {
}
