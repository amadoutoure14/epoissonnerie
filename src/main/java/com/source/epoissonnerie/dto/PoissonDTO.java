package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.entites.TypePoisson;

import java.util.Date;

public record PoissonDTO(
         Long id,
         TypePoisson type,
         String description,
         int quantite,
         double prix,
         boolean publier,
         Date date,
         Categorie categorie
) {
}
