package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.EvaluationModelAssembleur;
import com.source.epoissonnerie.controller.EvaluationController;
import com.source.epoissonnerie.dto.EvaluationDTO;
import com.source.epoissonnerie.entites.Evaluation;
import com.source.epoissonnerie.exceptions.EvaluationIntrouvable;
import com.source.epoissonnerie.repositories.EvaluationRepo;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class EvaluationService {

    final public EvaluationRepo evaluationRepo;
    private final EvaluationModelAssembleur assembler;


    public EntityModel<Evaluation> une(Long id){

        Evaluation evaluation = evaluationRepo
                .findById(id)
                .orElseThrow(() -> new EvaluationIntrouvable(id));

        return EntityModel.of(evaluation,
                linkTo(methodOn(EvaluationController.class).une(id)).withSelfRel(),
                linkTo(methodOn(EvaluationController.class).liste()).withRel("evaluations"));

    }
    public CollectionModel<EntityModel<Evaluation>> liste(){
        List<EntityModel<Evaluation>> entityModelList = evaluationRepo
                .findAll()
                .stream()
                .map(evaluation -> EntityModel.of(evaluation,
                        linkTo(methodOn(EvaluationController.class).une(evaluation.getId())).withSelfRel(),
                        linkTo(methodOn(EvaluationController.class).liste()).withRel("evaluations")))
                .collect(Collectors.toList());

        return CollectionModel.of(entityModelList, linkTo(methodOn(EvaluationController.class).liste()).withSelfRel());
    }

    public ResponseEntity<?> nouvelle(Evaluation evaluation) {
        EntityModel<EvaluationDTO> entityModel = assembler.toModel(evaluationRepo.save(evaluation));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Long id, Evaluation evaluation) {
        Evaluation evaluationOptional = evaluationRepo
                .findById(id)
                .map(
                        maj -> {
                            maj.setNote(evaluation.getNote());
                            return evaluationRepo.save(maj);
                        })
                .orElseGet(
                        () -> {
                            evaluation.setId(id);
                            return evaluationRepo.save(evaluation);
                        });
        EntityModel<EvaluationDTO> entityModel = assembler.toModel(evaluationOptional);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }
    public ResponseEntity<?> supprimer(Long id) {
        evaluationRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
