package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.*;

import java.util.Date;
import java.util.List;

public record PoissonDTO(
         Long id,
         TypePoisson type,
         String description,
         int quantite,
         double prix,
         boolean publier,
         Date date,
         Categorie categorie,
         Commande commande,
         Evaluation evaluation,
         Commentaire commentaire
) {
}
