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
@RequestMapping(value = "/controller")
public class PanierController {
    private final PanierService service;
    @PostMapping(value ="/ajouter")
    public ResponseEntity<Panier> ajouter(@Valid @RequestBody Panier panier) {
        return service.ajouter(panier);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Panier> lePanier(@PathVariable Long id) {
        return service.lePanier(id);
    }
    @PutMapping(value = "/modifier/{id}")
    public ResponseEntity<Panier> modifier(@PathVariable Long id, @RequestBody Panier panier) {
        return service.modifier(id, panier);
    }
    @PatchMapping(value = "/partiel/{id}")
    public ResponseEntity<Panier> partiel(@PathVariable Long id, @RequestBody Map<String, Object> panier) {
        return service.partiel(id, panier);
    }
    @DeleteMapping(value = "/supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        return service.supprimer(id);
    }
    @GetMapping(value = "/liste")
    public ResponseEntity<List<Panier>> liste() {
        return service.liste();
    }
}
