package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.PoissonDTO;
import com.source.epoissonnerie.entites.Poisson;

import java.util.function.Function;

public class PoissonMapper implements Function<Poisson, PoissonDTO> {
    @Override
    public PoissonDTO apply(Poisson poisson) {
        return new PoissonDTO(poisson.getId(),poisson.getType(),poisson.getDescription(),poisson.getQuantite(),poisson.getPrix(),poisson.isPublier(),poisson.getDate(),poisson.getCategorie());
    }
}
