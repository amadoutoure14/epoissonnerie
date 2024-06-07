package com.source.epoissonnerie.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public record ClientDTO(
        Long id,
        String nom,
        String mdp,
        String adresse,
        int tel
) {
}
