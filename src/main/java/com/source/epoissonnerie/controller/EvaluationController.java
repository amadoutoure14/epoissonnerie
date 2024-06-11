package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Evaluation;
import com.source.epoissonnerie.services.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/evaluation",consumes = MediaType.APPLICATION_JSON_VALUE)
public class EvaluationController {
    private EvaluationService evaluationService;
    @PostMapping
    ResponseEntity<?> nouvelle(@RequestBody Evaluation evaluation){
        return evaluationService.nouvelle(evaluation);
    }
    @GetMapping("/{id}")
    public EntityModel<Evaluation> une(@PathVariable Long id) {
        return evaluationService.une(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Evaluation>> liste() {
        return evaluationService.liste();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id,@RequestBody Evaluation evaluation) {
        return evaluationService.modifier(id,evaluation);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return evaluationService.supprimer(id);
    }

}
