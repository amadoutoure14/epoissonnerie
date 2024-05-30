package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Vendeur;
import com.source.epoissonnerie.entites.Visiteur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class VisiteurModelAssembleur implements RepresentationModelAssembler<Visiteur, EntityModel<Visiteur>> {
    @Override
    public EntityModel<Visiteur> toModel(Visiteur visiteur) {
        return EntityModel
                .of(
                        visiteur,
                        linkTo(methodOn(VendeurController.class).un(visiteur.getId())).withSelfRel(),
                        linkTo(methodOn(VendeurController.class).tout()).withRel("vendeurs")
                );
    }
}
