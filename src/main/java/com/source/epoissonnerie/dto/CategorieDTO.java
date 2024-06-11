package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Poisson;
import com.source.epoissonnerie.entites.Publication;
import com.source.epoissonnerie.entites.Vendeur;

import java.util.Date;
import java.util.List;

public record CategorieDTO(
        Long id,
        String nom,
        String description,
        boolean publier,
        Vendeur vendeur,
        Date date,
        List<Poisson> poissons,
        Publication publication
){
}
