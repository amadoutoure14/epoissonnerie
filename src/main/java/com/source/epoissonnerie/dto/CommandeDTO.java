package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Status;

public record CommandeDTO(
        Long id,
        String description,
        Status status
) {
}
