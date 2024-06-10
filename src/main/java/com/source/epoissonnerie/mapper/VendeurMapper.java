package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.VendeurDTO;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class VendeurMapper implements Function<Vendeur, VendeurDTO> {
    @Override
    public VendeurDTO apply(Vendeur vendeur) {
        return new VendeurDTO(vendeur.getId(),vendeur.getNom(),vendeur.getAdresse(),vendeur.getTel(),vendeur.getMdp(),vendeur.getDate(),vendeur.getCategories());
    }
}
