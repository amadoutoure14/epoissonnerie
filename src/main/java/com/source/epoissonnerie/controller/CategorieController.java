package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.services.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public EntityModel<Categorie> une(@PathVariable Long id) {
        return categorieService.une(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Categorie>> toute() {
        return categorieService.toute();
    }
    @PutMapping("/id")
    public ResponseEntity<?> update(@RequestBody Categorie categorie) {
        return null;
    }
}
