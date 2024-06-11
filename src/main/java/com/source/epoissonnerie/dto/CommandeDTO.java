package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Achat;
import com.source.epoissonnerie.entites.Panier;
import com.source.epoissonnerie.entites.Poisson;
import com.source.epoissonnerie.entites.Status;

import java.util.Date;
import java.util.List;

public record CommandeDTO(
        Long id,
        String description,
        Status status,
        Date date,
        Panier panier,
        Achat achat,
        List<Poisson> poissons
) {
}
