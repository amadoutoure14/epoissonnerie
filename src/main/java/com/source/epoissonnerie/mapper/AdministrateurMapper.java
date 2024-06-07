package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.AdministrateurDTO;
import com.source.epoissonnerie.entites.Administrateur;

import java.util.function.Function;

public class AdministrateurMapper implements Function<Administrateur, AdministrateurDTO> {
    @Override
    public AdministrateurDTO apply(Administrateur administrateur) {
        return new AdministrateurDTO(administrateur.getId(),administrateur.getNom(),administrateur.getMdp(),administrateur.getTel(),administrateur.getEmail());
    }
}
