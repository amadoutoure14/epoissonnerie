package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.EvaluationController;
import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Evaluation;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EvaluationModelAssembleur implements RepresentationModelAssembler<Evaluation, EntityModel<Evaluation>> {
    @Override
    public EntityModel<Evaluation> toModel(Evaluation evaluation) {
        return EntityModel
                .of(
                        evaluation,
                        linkTo(methodOn(EvaluationController.class).un(evaluation.getId())).withSelfRel(),
                        linkTo(methodOn(EvaluationController.class).tout()).withRel("evaluations")
                );
    }
}
