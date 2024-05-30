package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.entites.Evaluation;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EvaluationModelAssembleur implements RepresentationModelAssembler<Evaluation, EntityModel<Evaluation>> {
    @Override
    public EntityModel<Evaluation> toModel(Evaluation evaluation) {
        return null;
    }
}
