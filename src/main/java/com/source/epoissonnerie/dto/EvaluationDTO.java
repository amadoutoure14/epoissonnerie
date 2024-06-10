package com.source.epoissonnerie.dto;

import com.source.epoissonnerie.entites.Poisson;

import javax.xml.crypto.Data;
import java.util.Date;

public record EvaluationDTO(
        Long id,
        int note,
        Date date,
        Poisson poisson
) {
}
