package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Administrateur;
import com.source.epoissonnerie.services.AdministrateurService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/admin",consumes = MediaType.APPLICATION_JSON_VALUE)
public class AdministrateurController {
    private AdministrateurService administrateurService;
    @PostMapping
    ResponseEntity<?> nouveau(@RequestBody Administrateur administrateur){
        return administrateurService.nouveau(administrateur);
    }
    @GetMapping("/{id}")
    public EntityModel<Administrateur> un(@PathVariable Long id) {
        return administrateurService.un(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Administrateur>> liste() {
        return administrateurService.liste();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id,@RequestBody Administrateur administrateur) {
        return administrateurService.modifier(id,administrateur);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> modifierPartiel(@PathVariable Long id,@RequestBody Map<String, Object> administrateur) {
        return administrateurService.modifierPartiel(id,administrateur);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return administrateurService.supprimer(id);
    }


}
