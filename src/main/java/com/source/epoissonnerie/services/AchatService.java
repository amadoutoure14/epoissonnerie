package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.AchatModelAssembleur;
import com.source.epoissonnerie.controller.AchatController;
import com.source.epoissonnerie.entites.Achat;
import com.source.epoissonnerie.exceptions.AchatIntrouvable;
import com.source.epoissonnerie.exceptions.CategorieIntrouvable;
import com.source.epoissonnerie.repositories.AchatRepo;
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
public class AchatService {

    final public AchatRepo achatRepo;
    private final AchatModelAssembleur assembler;
    public EntityModel<Achat> un(Long id){

        Achat achat = achatRepo
                .findById(id)
                .orElseThrow(
                        () -> new AchatIntrouvable(id)
                );

        return EntityModel.of(
                achat,
                linkTo(
                        methodOn(AchatController.class)
                        .un(id))
                        .withSelfRel(),
                linkTo(
                        methodOn(AchatController.class)
                                .liste())
                        .withRel("achats"));
    }
    public CollectionModel<EntityModel<Achat>> liste(){
        List<EntityModel<Achat>> AchatList = achatRepo
                .findAll()
                .stream()
                .map(
                        achat -> EntityModel.of(achat,
                        linkTo(methodOn(AchatController.class).un(achat.getId())).withSelfRel(),
                        linkTo(methodOn(AchatController.class).liste()).withRel("achats")))
                .collect(Collectors.toList());

        return CollectionModel.of(AchatList, linkTo(methodOn(AchatController.class).liste()).withSelfRel());
    }

    public ResponseEntity<?> nouveauAchat(Achat achat) {
        EntityModel<Achat> entityModel = assembler.toModel(achatRepo.save(achat));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Long id,Achat achat) {
        Achat achatOptional = achatRepo
                .findById(id)
                .map(
                        maj -> {
                            maj.setPrix(achat.getPrix());
                            maj.setMontant(achat.getMontant());
                            maj.setQuantite(achat.getQuantite());
                            return achatRepo.save(maj);
                        })
                .orElseGet(
                        () -> {
                            achat.setId(id);
                            return achatRepo.save(achat);
                        });
        EntityModel<Achat> entityModel = assembler.toModel(achatOptional);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifierPartiel(Long id, Map<String, Object> achat) {
        Achat achatOptional = achatRepo
                .findById(id)
                .orElseThrow(
                        () -> new AchatIntrouvable(id)
                );
        achat.forEach(
                (key, value) -> {
                    switch (key) {
                        case "quantite":
                            achatOptional.setQuantite((int) value);
                            break;
                        case "prix":
                            achatOptional.setPrix((double) value);
                            break;
                        case "montant":
                            achatOptional.setMontant((double) value);
                            break;
                        default:
                            throw new AchatIntrouvable( id);
                    }
                });

        EntityModel<Achat> entityModel = assembler.toModel( achatRepo.save(achatOptional));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    public ResponseEntity<?> supprimer(Long id) {
        achatRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
