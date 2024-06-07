package com.source.epoissonnerie.dto;
public record VendeurDTO(
        Long id,
        String nom,
        String adresse,
        int tel,
        String mdp
) {

}
