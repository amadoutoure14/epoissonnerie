package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.PanierDTO;
import com.source.epoissonnerie.entites.Panier;

import java.util.function.Function;

public class PanierMapper implements Function<Panier, PanierDTO> {
    @Override
    public PanierDTO apply(Panier panier) {
        return new PanierDTO(panier.getId(), panier.getNom(),panier.getDescription(),panier.getPrix_total(),panier.getQuantite(),panier.getUtilisateur());
    }
}
