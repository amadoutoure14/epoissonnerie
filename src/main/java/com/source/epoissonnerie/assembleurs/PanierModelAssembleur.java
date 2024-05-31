package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.PanierController;
import com.source.epoissonnerie.entites.Panier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PanierModelAssembleur implements RepresentationModelAssembler<Panier, EntityModel<Panier>> {
    @Override
    public EntityModel<Panier> toModel(Panier panier) {
        return EntityModel
                .of(
                        panier,
                        linkTo(methodOn(PanierController.class).un(panier.getId())).withSelfRel(),
                        linkTo(methodOn(PanierController.class).liste()).withRel("paniers")
                );
    }
}
