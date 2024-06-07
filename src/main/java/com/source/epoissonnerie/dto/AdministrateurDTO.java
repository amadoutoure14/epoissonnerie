package com.source.epoissonnerie.dto;

public record AdministrateurDTO(
        Long id,
        String nom,
        String mdp,
        int tel,
        String email
){
}
