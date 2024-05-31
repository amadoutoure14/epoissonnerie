package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.AchatController;
import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Achat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AchatModelAssembleur implements RepresentationModelAssembler<Achat, EntityModel<Achat>> {
    @Override
    public EntityModel<Achat> toModel(Achat achat) {
        return EntityModel
                .of(
                        achat,
                        linkTo(methodOn(AchatController.class).un(achat.getId())).withSelfRel(),
                        linkTo(methodOn(AchatController.class).tout()).withRel("achats")
                );
    }
}
