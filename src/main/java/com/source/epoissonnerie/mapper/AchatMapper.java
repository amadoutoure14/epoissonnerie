package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.AchatDTO;
import com.source.epoissonnerie.entites.Achat;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AchatMapper implements Function<Achat, AchatDTO> {
    @Override
    public AchatDTO apply(Achat achat) {
        return new AchatDTO(achat.getId(), achat.getPrix(),achat.getMontant(),achat.getQuantite(),achat.getPanier());
    }
}
