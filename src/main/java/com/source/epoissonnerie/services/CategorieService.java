package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.CollectionModelAssembleur;
import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.exceptions.CollectionIntrouvable;
import com.source.epoissonnerie.repositories.CollectionRepo;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategorieService {

    final private CollectionRepo collectionRepo;
    final private CollectionModelAssembleur assembleur;

    public ResponseEntity<?> nouvelle(Categorie categorie) {
        EntityModel<Categorie> entityModel = assembleur.toModel(collectionRepo.save(categorie));
        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public EntityModel<Categorie> une(Long id) {
        Categorie categorie = collectionRepo
                .findById(id)
                .orElseThrow(
                        () -> new CollectionIntrouvable(id)
                );
        return assembleur.toModel(categorie);
    }

    public CollectionModel<EntityModel<Categorie>> toute() {
        return null;
    }
}
