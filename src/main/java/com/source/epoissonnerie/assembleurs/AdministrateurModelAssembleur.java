package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.entites.Administrateur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AdministrateurModelAssembleur implements RepresentationModelAssembler<Administrateur, EntityModel<Administrateur>> {
    @Override
    public EntityModel<Administrateur> toModel(Administrateur entity) {
        return null;
    }
}
