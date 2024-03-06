package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Panier;
import com.source.epoissonnerie.services.PanierService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Builder
@RequestMapping(path = "controller")
public class PanierController {
    private final PanierService service;
    @PostMapping(path ="ajouter")
    public ResponseEntity<Panier> ajouter(@Valid @RequestBody Panier panier) {
        return service.ajouter(panier);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<Panier> lePanier(@PathVariable Long id) {
        return service.lePanier(id);
    }
    @PutMapping(path = "modifier/{id}")
    public ResponseEntity<Panier> modifier(@PathVariable Long id, @RequestBody Panier panier) {
        return service.modifier(id, panier);
    }
    @PatchMapping(path = "partiel/{id}")
    public ResponseEntity<Panier> partiel(@PathVariable Long id, @RequestBody Map<String, Object> panier) {
        return service.partiel(id, panier);
    }
    @DeleteMapping(path = "supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        return service.supprimer(id);
    }
    @GetMapping(path = "iste")
    public ResponseEntity<List<Panier>> liste() {
        return service.liste();
    }
}
