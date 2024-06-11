package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.dto.CategorieDTO;
import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.services.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/categorie")
@AllArgsConstructor
public class CategorieController {
    private CategorieService categorieService;
    @PostMapping
    ResponseEntity<?> nouvelle(@RequestBody Categorie categorie){
        return categorieService.nouvelle(categorie);
    }
    @GetMapping("/{id}")
    public EntityModel<CategorieDTO> une(@PathVariable Long id) {
        return categorieService.une(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Categorie>> liste() {
        return categorieService.liste();
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
    @GetMapping("/filtre")
    public ResponseEntity<?> nomFiltre(@RequestParam String nom) {
        return categorieService.nomFiltre(nom);
    }
}
