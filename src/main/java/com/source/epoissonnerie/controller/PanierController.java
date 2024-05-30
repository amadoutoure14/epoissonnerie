package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.services.CategorieService;
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
    private CategorieService categorieService;
    @PostMapping
    ResponseEntity<?> nouvelle(@RequestBody Categorie categorie){
        return categorieService.nouvelle(categorie);
    }
    @GetMapping("/{id}")
    public EntityModel<Categorie> une(@PathVariable Long id) {
        return categorieService.une(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Categorie>> toute() {
        return categorieService.toute();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id,@RequestBody Categorie categorie) {
        return categorieService.modifier(id,categorie);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> modifierPartiel(@PathVariable Long id,@RequestBody Map<String, Object> categorie) {
        return categorieService.modifierPartiel(id,categorie);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return categorieService.supprimer(id);
    }
}
