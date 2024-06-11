package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Panier;
import com.source.epoissonnerie.services.PanierService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/panier",consumes = MediaType.APPLICATION_JSON_VALUE)
public class PanierController {
    private PanierService panierService;
    @PostMapping
    ResponseEntity<?> nouveau(@RequestBody Panier panier){
        return panierService.nouveau(panier);
    }
    @GetMapping("/{id}")
    public EntityModel<Panier> un(@PathVariable Long id) {
        return panierService.un(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Panier>> liste() {
        return panierService.liste();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id,@RequestBody Panier panier) {
        return panierService.modifier(id,panier);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> modifierPartiel(@PathVariable Long id,@RequestBody Map<String, Object> panier) {
        return panierService.modifierPartiel(id,panier);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return panierService.supprimer(id);
    }


}
