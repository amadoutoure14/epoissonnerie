package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.entites.Achat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AchatModelAssembleur implements RepresentationModelAssembler<Achat, EntityModel<Achat>> {
    @Override
    public EntityModel<Achat> toModel(Achat achat) {
        return null;
    }
}
