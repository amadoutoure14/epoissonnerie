package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.VisiteurModelAssembleur;
import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Visiteur;
import com.source.epoissonnerie.repositories.VisiteurRepo;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class VisiteurService {

    final public VisiteurRepo visiteurRepo;
    private final VisiteurModelAssembleur assembler;


    public EntityModel<Visiteur> un(Long id){

        Visiteur visiteur = visiteurRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException(" visiteur non trouver!"));

        return EntityModel.of(visiteur,
                linkTo(methodOn(VendeurController.class).un(id)).withSelfRel(),
                linkTo(methodOn(VendeurController.class).liste()).withRel("visiteurs"));

    }
    public CollectionModel<EntityModel<Visiteur>> liste(){
        List<EntityModel<Visiteur>> visiteurs = visiteurRepo
                .findAll()
                .stream()
                .map(visiteur -> EntityModel.of(visiteur,
                        linkTo(methodOn(VendeurController.class).un(visiteur.getId())).withSelfRel(),
                        linkTo(methodOn(VendeurController.class).liste()).withRel("visiteurs")))
                .collect(Collectors.toList());

        return CollectionModel.of(visiteurs, linkTo(methodOn(VendeurController.class).liste()).withSelfRel());
    }

    public ResponseEntity<?> nouveau(Visiteur visiteur) {
        EntityModel<Visiteur> entityModel = assembler.toModel(visiteurRepo.save(visiteur));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Long id,Visiteur visiteur) {
        Visiteur visiteurOptional = visiteurRepo
                .findById(id)
                .map(
                        maj -> {
                            maj.setDate(visiteur.getDate());
                            return visiteurRepo.save(maj);
                        })
                .orElseGet(
                        () -> {
                            visiteur.setId(id);
                            return visiteurRepo.save(visiteur);
                        });
        EntityModel<Visiteur> entityModel = assembler.toModel(visiteurOptional);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifierPartiel(Long id, Map<String, Object> visiteur) {
        Visiteur visiteurOptional = visiteurRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException("visiteur non trouver"));
        visiteur.forEach(
                (key, value) -> {
                    if (key.equals("nom")) {
                        visiteurOptional.setDate((Date) value);
                    } else {
                        throw new NotFoundException("visiteur non trouver");
                    }
                });

        EntityModel<Visiteur> entityModel = assembler.toModel( visiteurRepo.save(visiteurOptional));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    public ResponseEntity<?> supprimer(Long id) {
        visiteurRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
