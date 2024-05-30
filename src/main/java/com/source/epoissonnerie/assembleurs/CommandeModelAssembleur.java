package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.entites.Commande;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CommandeModelAssembleur implements RepresentationModelAssembler<Commande, EntityModel<Commande>> {
    @Override
    public EntityModel<Commande> toModel(Commande commande) {
        return null;
    }
}
