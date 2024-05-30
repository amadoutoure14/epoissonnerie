package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public class UtilisateurModelAssembleur implements RepresentationModelAssembler<Utilisateur, EntityModel<Utilisateur>> {
    @Override
    public EntityModel<Utilisateur> toModel(Utilisateur utilisateur) {
        return null;
    }
}
