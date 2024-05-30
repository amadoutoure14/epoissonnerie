package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Panier;
import com.source.epoissonnerie.entites.Vendeur;
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
                        linkTo(methodOn(VendeurController.class).un(panier.getId())).withSelfRel(),
                        linkTo(methodOn(VendeurController.class).tout()).withRel("paniers")
                );
    }
}
