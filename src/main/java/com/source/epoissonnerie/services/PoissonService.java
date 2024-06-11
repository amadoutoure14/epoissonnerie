package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.PoissonModelAssembleur;
import com.source.epoissonnerie.controller.PoissonController;
import com.source.epoissonnerie.dto.PoissonDTO;
import com.source.epoissonnerie.entites.Poisson;
import com.source.epoissonnerie.entites.TypePoisson;
import com.source.epoissonnerie.exceptions.PoissonIntrouvable;
import com.source.epoissonnerie.repositories.PoissonRepo;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class PoissonService {

    final private PoissonRepo poissonRepo;
    final private PoissonModelAssembleur assembler;


    public EntityModel<Poisson> un(Long id){

        Poisson poisson = poissonRepo
                .findById(id)
                .orElseThrow(() -> new PoissonIntrouvable(id));

        return EntityModel.of(poisson,
                linkTo(methodOn(PoissonController.class).un(id)).withSelfRel(),
                linkTo(methodOn(PoissonController.class).liste()).withRel("poissons"));

    }
    public CollectionModel<EntityModel<Poisson>> liste(){
        List<EntityModel<Poisson>> entityModelList = poissonRepo
                .findAll()
                .stream()
                .map(
                        poisson -> EntityModel.of(
                                poisson,
                        linkTo(methodOn(PoissonController.class).un(poisson.getId())).withSelfRel(),
                        linkTo(methodOn(PoissonController.class).liste()).withRel("entityModelList")))
                .collect(Collectors.toList());

        return CollectionModel.of(entityModelList, linkTo(methodOn(PoissonController.class).liste()).withSelfRel());
    }

    public ResponseEntity<?> nouveau(Poisson poisson) {
        EntityModel<PoissonDTO> entityModel = assembler.toModel(poissonRepo.save(poisson));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Long id,Poisson poisson) {
        Poisson optionalPoisson = poissonRepo
                .findById(id)
                .map(
                        maj -> {
                            maj.setQuantite(poisson.getQuantite());
                            maj.setDescription(poisson.getDescription());
                            maj.setType(poisson.getType());
                            maj.setPrix(poisson.getPrix());
                            maj.setDate(poisson.getDate());
                            return poissonRepo.save(maj);
                        })
                .orElseGet(
                        () -> {
                            poisson.setId(id);
                            return poissonRepo.save(poisson);
                        });
        EntityModel<PoissonDTO> entityModel = assembler.toModel(optionalPoisson);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifierPartiel(Long id, Map<String, Object> poisson) {
        Poisson poissonOptional = poissonRepo
                .findById(id)
                .orElseThrow(() -> new PoissonIntrouvable(id));
        poisson.forEach(
                (key, value) -> {
                    switch (key) {
                        case "date":
                            poissonOptional.setDate((Date) value);
                            break;
                        case "publier":
                            poissonOptional.setPublier((boolean) value);
                            break;
                        case "adresse":
                            poissonOptional.setType((TypePoisson) value);
                            break;
                            case "quantite":
                            poissonOptional.setQuantite((Integer) value);
                            break;
                            case "prix":
                            poissonOptional.setPrix((Integer) value);
                            break;
                        default:
                            throw new PoissonIntrouvable( id);
                    }
                });

        EntityModel<PoissonDTO> entityModel = assembler.toModel( poissonRepo.save(poissonOptional));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    public ResponseEntity<?> supprimer(Long id) {
        poissonRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
