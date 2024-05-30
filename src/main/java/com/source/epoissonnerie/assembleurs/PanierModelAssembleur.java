package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.entites.Panier;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PanierModelAssembleur implements RepresentationModelAssembler<Panier, EntityModel<Panier>> {
    @Override
    public EntityModel<Panier> toModel(Panier panier) {
        return null;
    }
}
