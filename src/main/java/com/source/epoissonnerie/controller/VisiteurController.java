package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.entites.Visiteur;
import com.source.epoissonnerie.services.VisiteurService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/visiteur",consumes = MediaType.APPLICATION_JSON_VALUE)
public class VisiteurController {

    final private VisiteurService visiteurService;
    @PostMapping
    ResponseEntity<?> nouveau(@RequestBody Visiteur visiteur){
        return visiteurService.nouveau(visiteur);
    }
    @GetMapping("/{id}")
    public EntityModel<Visiteur> un(@PathVariable Long id) {
        return visiteurService.un(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Visiteur>> liste() {
        return visiteurService.liste();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id,@RequestBody Visiteur visiteur) {
        return visiteurService.modifier(id,visiteur);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> modifierPartiel(@PathVariable Long id,@RequestBody Map<String, Object> visiteur) {
        return visiteurService.modifierPartiel(id,visiteur);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return visiteurService.supprimer(id);
    }


}
