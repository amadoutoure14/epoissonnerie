package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.entites.Commentaire;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CommentaireModelAssembleur implements RepresentationModelAssembler<Commentaire, EntityModel<Commentaire>> {
    @Override
    public EntityModel<Commentaire> toModel(Commentaire commentaire) {
        return null;
    }
}
