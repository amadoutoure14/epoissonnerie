package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Poisson;
import com.source.epoissonnerie.services.PoissonService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/poisson",consumes = MediaType.APPLICATION_JSON_VALUE)
public class PoissonController {
    private PoissonService poissonService;
    @PostMapping
    ResponseEntity<?> nouveau(@RequestBody Poisson poisson){
        return poissonService.nouveau(poisson);
    }
    @GetMapping("/{id}")
    public EntityModel<Poisson> un(@PathVariable Long id) {
        return poissonService.un(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Poisson>> liste() {
        return poissonService.liste();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id,@RequestBody Poisson poisson) {
        return poissonService.modifier(id,poisson);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> modifierPartiel(@PathVariable Long id,@RequestBody Map<String, Object> poisson) {
        return poissonService.modifierPartiel(id,poisson);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return poissonService.supprimer(id);
    }

}
