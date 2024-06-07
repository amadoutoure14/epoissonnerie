package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Poisson;
import com.source.epoissonnerie.entites.Utilisateur;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CategorieDTO(
        Long id,
        String nom,
        String description,
        boolean publier,
        Utilisateur utilisateur,
        List<Poisson> poissons
){
}
