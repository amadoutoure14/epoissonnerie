package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Categorie;

import java.util.Date;
import java.util.List;

public record VendeurDTO(
        Long id,
        String nom,
        String adresse,
        int tel,
        String mdp,
        Date date,
        List<Categorie> categories
) {

}
