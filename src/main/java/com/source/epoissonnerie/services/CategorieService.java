package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.CategorieModelAssembleur;
import com.source.epoissonnerie.controller.CategorieController;
import com.source.epoissonnerie.dto.CategorieDTO;
import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.entites.Publication;
import com.source.epoissonnerie.exceptions.CategorieIntrouvable;
import com.source.epoissonnerie.repositories.CategorieRepo;
import com.source.epoissonnerie.repositories.PublicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class CategorieService {

    final private CategorieRepo categorieRepo;
    final private CategorieModelAssembleur assembleur;
    final private PublicationRepository publicationRepo;

    public ResponseEntity<?> nouvelle(Categorie categorie) {

        String nom = categorie.getNom();
        categorie.setNom(nom.toLowerCase());


        if (categorie.isPublier()){
            Publication publication = new Publication();
            publication.setVendeur(categorie.getVendeur());
            publication.setTitre(categorie.getNom());
            categorie.setPublication(publication);
            Publication save = publicationRepo.save(publication);
        }

        Categorie nouvelleCategorie = categorieRepo.save(categorie);

        EntityModel<CategorieDTO> entityModel = assembleur.toModel(nouvelleCategorie);
        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public EntityModel<CategorieDTO> une(Long id) {
        Categorie categorie = categorieRepo
                .findById(id)
                .orElseThrow(
                        () -> new CategorieIntrouvable(id)
                );
        return assembleur.toModel(categorie);
    }

    public CollectionModel<EntityModel<Categorie>> liste() {
        List<EntityModel<Categorie>> categories = categorieRepo
                .findAll()
                .stream()
                .map(
                        categorie -> EntityModel.of(
                              categorie,
                                linkTo(methodOn(CategorieController.class).une(categorie.getId())).withSelfRel(),
                                linkTo(methodOn(CategorieController.class).liste()).withRel("catégories")
                        )
                )
                .collect(Collectors.toList());
        return CollectionModel.of(
                categories,linkTo(methodOn(CategorieController.class).liste()).withSelfRel()
        );
    }

    public ResponseEntity<?> modifier(Long id, Categorie categorie) {
        Categorie categorieOptional = categorieRepo.findById(id)
                .map(
                        maj -> {
                            maj.setNom(categorie.getNom());
                            maj.setDescription(categorie.getDescription());
                            maj.setPublier(categorie.isPublier());

                            Publication publication = categorie.getPublication();
                            Long idPublication = publication.getId();
                            if (!categorie.isPublier()) {
                                publicationRepo.deleteById(idPublication);
                            }
                            else {
                                publication.setVendeur(maj.getVendeur());
                                publication.setTitre(maj.getNom());
                                maj.setPublication(publication);
                                Publication save = publicationRepo.save(publication);

                            }

                            return categorieRepo.save(maj);
                        }

                )
                .orElseGet(
                () -> {
                    categorie.setId(id);
                    return categorieRepo.save(categorie);
                }
        );

        EntityModel<CategorieDTO> categorieEntityModel = assembleur.toModel(categorieOptional);
        return ResponseEntity
                .created(
                        categorieEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()
                )
                .body(categorieEntityModel);
    }

    public ResponseEntity<?> modifierPartiel(Long id, Map<String, Object> categorie) {
        Categorie categorieOptional = categorieRepo
                .findById(id)
                .orElseThrow(
                        () -> new CategorieIntrouvable(id));
        categorie.forEach(
                (key, valeur) -> {
            switch (key){
                case "nom":
                    categorieOptional.setNom((String) valeur);
                    break;
                case "description":
                    categorieOptional.setDescription((String) valeur);
                    break;
                case "publier":
                    categorieOptional.setPublier((boolean) valeur);
                    Publication publication = categorieOptional.getPublication();
                    if (!categorieOptional.isPublier()){
                        publicationRepo.deleteById(publication.getId());
                    }
                    else {
                        Publication nouvellePublication = new Publication();
                        nouvellePublication.setDate(categorieOptional.getDate());
                        nouvellePublication.setTitre(categorieOptional.getNom());
                        nouvellePublication.setVendeur(categorieOptional.getVendeur());
                        publicationRepo.save(nouvellePublication);
                    }
                default:
                    throw new CategorieIntrouvable(id);
            }
        });

        EntityModel<CategorieDTO> categorieEntityModel = assembleur.toModel(categorieRepo.save(categorieOptional));
        return ResponseEntity.created(categorieEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(categorieEntityModel);
    }

    public ResponseEntity<?> supprimer(Long id) {
        categorieRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> nomFiltre(String nom) {
        String nomLower = nom.toLowerCase() ;
        Categorie categorie = categorieRepo.findByNom(nomLower).orElseThrow(() -> new RuntimeException("Le nom renseigne n'existe pas!"));
        return ResponseEntity.ok(categorie);
    }
}
