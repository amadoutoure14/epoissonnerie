package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Evaluation;
import com.source.epoissonnerie.services.EvaluationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/evaluation",consumes = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EvaluationController {
    public final EvaluationService service;
    @PostMapping(value = "/ajouter")
    public ResponseEntity<Evaluation> ajouter(@Valid @RequestBody Evaluation evaluation){
        return service.ajouter(evaluation);
    }
    @GetMapping(value = "/liste")
    public ResponseEntity<List<Evaluation>> liste(){
        return service.liste();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Evaluation> unEvaluation(@PathVariable Long id){
        return service.uneEvaluation(id);
    }
    @PutMapping(value = "/modifier/{id}")
    public ResponseEntity<Evaluation> modifier(@Valid @PathVariable Long id, @Valid @RequestBody Evaluation evaluation){
        return service.modifier(id, evaluation);
    }
    @DeleteMapping(value = "/supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        return service.supprimer(id);
    }
}
