package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.UtilisateurController;
import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class UtilisateurModelAssembleur implements RepresentationModelAssembler<Utilisateur, EntityModel<Utilisateur>> {
    @Override
    public EntityModel<Utilisateur> toModel(Utilisateur utilisateur) {
        return EntityModel
                .of(
                        utilisateur,
                        linkTo(methodOn(UtilisateurController.class).un()).withSelfRel(),
                        linkTo(methodOn(UtilisateurController.class).tout()).withRel("utilisateurs")
                );
    }
}
