package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.EvaluationDTO;
import com.source.epoissonnerie.entites.Evaluation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EvaluationMapper implements Function<Evaluation, EvaluationDTO> {
    @Override
    public EvaluationDTO apply(Evaluation evaluation) {
        return new EvaluationDTO(evaluation.getId(),evaluation.getNote(),evaluation.getDate(),evaluation.getPoisson(),evaluation.getClient());
    }
}
