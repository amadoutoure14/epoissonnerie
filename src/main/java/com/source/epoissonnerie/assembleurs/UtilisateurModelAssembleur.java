package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.UtilisateurController;
import com.source.epoissonnerie.entites.Utilisateur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class UtilisateurModelAssembleur implements RepresentationModelAssembler<Utilisateur, EntityModel<Utilisateur>> {
    @Override
    public EntityModel<Utilisateur> toModel(Utilisateur utilisateur) {
        return EntityModel
                .of(
                        utilisateur,
                        linkTo(methodOn(UtilisateurController.class).un(utilisateur.getId())).withSelfRel(),
                        linkTo(methodOn(UtilisateurController.class).liste()).withRel("utilisateurs")
                );
    }
}
