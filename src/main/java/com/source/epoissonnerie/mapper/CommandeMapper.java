package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.CommandeDTO;
import com.source.epoissonnerie.entites.Commande;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CommandeMapper implements Function<Commande, CommandeDTO> {
    @Override
    public CommandeDTO apply(Commande commande) {
        return new CommandeDTO(commande.getId(), commande.getDescription(),commande.getStatus(),commande.getDate(),commande.getPanier(),commande.getAchat(),commande.getPoissons());
    }
}
