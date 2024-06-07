package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.CategorieDTO;
import com.source.epoissonnerie.entites.Categorie;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategorieMapper implements Function<Categorie, CategorieDTO> {
    @Override
    public CategorieDTO apply(Categorie categorie) {
        return new CategorieDTO(categorie.getId(),categorie.getNom(),categorie.getDescription(),categorie.isPublier(),categorie.getUtilisateur(),categorie.getPoissons());
    }
}
