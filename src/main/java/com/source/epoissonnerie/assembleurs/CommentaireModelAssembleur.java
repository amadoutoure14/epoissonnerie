package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Commentaire;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommentaireModelAssembleur implements RepresentationModelAssembler<Commentaire, EntityModel<Commentaire>> {
    @Override
    public EntityModel<Commentaire> toModel(Commentaire commentaire) {
        return EntityModel
                .of(
                        commentaire,
                        linkTo(methodOn(VendeurController.class).un(commentaire.getId())).withSelfRel(),
                        linkTo(methodOn(VendeurController.class).tout()).withRel("commentaires")
                );
    }
}
