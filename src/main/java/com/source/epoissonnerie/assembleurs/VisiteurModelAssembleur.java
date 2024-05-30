package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.entites.Vendeur;
import com.source.epoissonnerie.entites.Visiteur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public class VisiteurModelAssembleur implements RepresentationModelAssembler<Visiteur, EntityModel<Visiteur>> {
    @Override
    public EntityModel<Visiteur> toModel(Visiteur visiteur) {
        return null;
    }
}
