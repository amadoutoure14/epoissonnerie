package com.source.epoissonnerie.assembleurs;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class VendeurModelAssembleur implements RepresentationModelAssembler<Vendeur, EntityModel<Vendeur>> {

    @Override
    public EntityModel<Vendeur> toModel(Vendeur vendeur) {
        return EntityModel
                .of(
                        vendeur,
                linkTo(methodOn(VendeurController.class).un(vendeur.getId())).withSelfRel(),
                linkTo(methodOn(VendeurController.class).liste()).withRel("vendeurs")
                );
    }
}