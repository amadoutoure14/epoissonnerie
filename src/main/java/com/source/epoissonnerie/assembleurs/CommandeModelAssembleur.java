package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Commande;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommandeModelAssembleur implements RepresentationModelAssembler<Commande, EntityModel<Commande>> {
    @Override
    public EntityModel<Commande> toModel(Commande commande) {
        return EntityModel
                .of(
                        commande,
                        linkTo(methodOn(VendeurController.class).un(commande.getId())).withSelfRel(),
                        linkTo(methodOn(VendeurController.class).tout()).withRel("commandes")
                );
    }
}
