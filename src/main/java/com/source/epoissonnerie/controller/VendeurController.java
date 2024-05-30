package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Vendeur;
import com.source.epoissonnerie.services.VendeurService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/vendeur",consumes = MediaType.APPLICATION_JSON_VALUE)
public class VendeurController {

    final private VendeurService vendeurService;
    @PostMapping("/nouveau")
    ResponseEntity<?> nouveauVendeur(@RequestBody Vendeur vendeur) {
        return vendeurService.nouveauVendeur(vendeur);
    }
    @GetMapping("/{id}")
    public EntityModel<Vendeur> un(@PathVariable Long id) {
        return vendeurService.un(id);
    }
    @GetMapping("/tout")
    public CollectionModel<EntityModel<Vendeur>> tout() {
        return vendeurService.tout();
    }
    @PutMapping("/{id}")
    ResponseEntity<?> modifier(@RequestBody Vendeur vendeur,@PathVariable Long id) {
        return vendeurService.modifier(vendeur,id);
    }
    @PatchMapping("/{id}")
    ResponseEntity<?> patch(@PathVariable Long id, @RequestBody Map<String, Object> vendeur) {
        return vendeurService.modifierPartiel(id,vendeur);
    }
}
