package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entity.Vendeur;
import com.source.epoissonnerie.service.VendeurService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/vendeur")
@Builder
public class VendeurController {
    public final VendeurService service;
    @PostMapping(value = "/ajouter")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vendeur> ajouter(@Valid @RequestBody Vendeur vendeur){
        return service.ajouter(vendeur);
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Vendeur> unVendeur(@PathVariable Long id){
        return service.unVendeur(id);
    }
    @GetMapping(value = "/listes")
    @ResponseStatus(HttpStatus.GONE)
    public List<Vendeur> liste() {
        return service.liste();
    }
    @PutMapping(value = "/modifier/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Vendeur> modifier(@Valid @PathVariable Long id, @RequestBody Vendeur vendeur){
        return service.modifier(id, vendeur);
    }
    @PatchMapping(value = "/partiel/{id}")
    public ResponseEntity<Vendeur> modifierPartiel(@Valid @PathVariable Long id, @RequestBody Map<String, Object> updates){
        return service.Partiel(id, updates);
    }
    @GetMapping("/filtre")
    public ResponseEntity<Vendeur> filtreVendeur(@Valid @RequestParam String nom, @RequestParam String prenom){
        return service.filtreVendeur(nom, prenom);
    }
}
