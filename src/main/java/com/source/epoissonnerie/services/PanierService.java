package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.PanierModelAssembleur;
import com.source.epoissonnerie.controller.PanierController;
import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.dto.PanierDTO;
import com.source.epoissonnerie.entites.Panier;
import com.source.epoissonnerie.exceptions.CategorieIntrouvable;
import com.source.epoissonnerie.exceptions.PanierIntrouvable;
import com.source.epoissonnerie.repositories.PanierRepo;
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
public class PanierService {

    final public PanierRepo panierRepo;
    private final PanierModelAssembleur assembler;


    public EntityModel<Panier> un(Long id){

        Panier panier = panierRepo
                .findById(id)
                .orElseThrow(() -> new PanierIntrouvable(id));

        return EntityModel.of(panier,
                linkTo(methodOn(PanierController.class).un(id)).withSelfRel(),
                linkTo(methodOn(PanierController.class).liste()).withRel("paniers"));

    }
    public CollectionModel<EntityModel<Panier>> liste(){
        List<EntityModel<Panier>> entityModelList = panierRepo
                .findAll()
                .stream()
                .map(panier -> EntityModel.of(panier,
                        linkTo(methodOn(PanierController.class).un(panier.getId())).withSelfRel(),
                        linkTo(methodOn(PanierController.class).liste()).withRel("paniers")))
                .collect(Collectors.toList());

        return CollectionModel.of(entityModelList, linkTo(methodOn(VendeurController.class).liste()).withSelfRel());
    }

    public ResponseEntity<?> nouveau(Panier panier) {
        EntityModel<PanierDTO> entityModel = assembler.toModel(panierRepo.save(panier));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Long id,Panier panier) {
        Panier optionalPanier = panierRepo
                .findById(id)
                .map(
                        maj -> {
                            maj.setNom(panier.getNom());
                            maj.setDescription(panier.getDescription());
                            maj.setQuantite(panier.getQuantite());
                            maj.setPrix_total(panier.getPrix_total());
                            return panierRepo.save(maj);
                        })
                .orElseGet(
                        () -> {
                            panier.setId(id);
                            return panierRepo.save(panier);
                        });
        EntityModel<PanierDTO> entityModel = assembler.toModel(optionalPanier);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifierPartiel(Long id, Map<String, Object> panier) {
        Panier panierOptional = panierRepo
                .findById(id)
                .orElseThrow(() -> new PanierIntrouvable(id));
        panier.forEach(
                (key, value) -> {
                    switch (key) {
                        case "nom":
                            panierOptional.setNom((String) value);
                            break;
                        case "tel":
                            panierOptional.setPrix_total((Integer) value);
                            break;
                        case "adresse":
                            panierOptional.setQuantite((Integer) value);
                            break;
                        default:
                            throw new CategorieIntrouvable( id);
                    }
                });

        EntityModel<PanierDTO> entityModel = assembler.toModel( panierRepo.save(panierOptional));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    public ResponseEntity<?> supprimer(Long id) {
        panierRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
