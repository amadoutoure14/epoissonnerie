package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Evaluation;
import com.source.epoissonnerie.services.EvaluationService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="evaluation")
@Builder
public class EvaluationController {
    public final EvaluationService service;
    @PostMapping(path = "ajouter")
    public ResponseEntity<Evaluation> ajouter(@Valid @RequestBody Evaluation evaluation){
        return service.ajouter(evaluation);
    }
    @GetMapping(path = "liste")
    public ResponseEntity<List<Evaluation>> liste(){
        return service.liste();
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<Evaluation> unEvaluation(@PathVariable Long id){
        return service.uneEvaluation(id);
    }
    @PutMapping(path = "modifier/{id}")
    public ResponseEntity<Evaluation> modifier(@Valid @PathVariable Long id, @Valid @RequestBody Evaluation evaluation){
        return service.modifier(id, evaluation);
    }
    @DeleteMapping(path = "supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        return service.supprimer(id);
    }
}
