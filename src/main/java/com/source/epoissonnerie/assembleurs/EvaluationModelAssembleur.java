package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.EvaluationController;

import com.source.epoissonnerie.dto.EvaluationDTO;
import com.source.epoissonnerie.entites.Evaluation;

import com.source.epoissonnerie.mapper.EvaluationMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class EvaluationModelAssembleur implements RepresentationModelAssembler<Evaluation, EntityModel<EvaluationDTO>> {
    final private EvaluationMapper mapper;
    @Override
    public EntityModel<EvaluationDTO> toModel(Evaluation evaluation) {
        EvaluationDTO evaluationDTO = mapper.apply(evaluation);
        return EntityModel
                .of(
                        evaluationDTO,
                        linkTo(methodOn(EvaluationController.class).une(evaluationDTO.id())).withSelfRel(),
                        linkTo(methodOn(EvaluationController.class).liste()).withRel("evaluations")
                );
    }
}
