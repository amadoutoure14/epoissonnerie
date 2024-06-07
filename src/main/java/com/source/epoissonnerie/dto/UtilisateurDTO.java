package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.entites.Panier;
import com.source.epoissonnerie.entites.Role;

import java.util.List;

public record UtilisateurDTO(
        Long id,
        String nom,
        String email,
        int tel,
        String mdp,
        boolean actif,
        Role role,
        List<Panier> paniers,
        List<Categorie> categories
) {
}
