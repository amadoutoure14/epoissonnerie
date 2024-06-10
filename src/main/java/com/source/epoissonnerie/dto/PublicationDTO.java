package com.source.epoissonnerie.dto;

import java.util.Date;

public record PublicationDTO(
        Long id,
        String titre,
        Date date
) {
}
