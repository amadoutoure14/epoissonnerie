package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Vendeur;
import com.source.epoissonnerie.services.VendeurService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "vendeur")
@Builder
public class VendeurController {
    public final VendeurService service;
    @PostMapping(path = "/inscription")
    public ResponseEntity<Vendeur> ajouter(@Valid @RequestBody Vendeur vendeur){
        return service.ajouter(vendeur);
    }
    @GetMapping(path="{id}")
    public ResponseEntity<Vendeur> unVendeur(@PathVariable Long id){
        return service.unVendeur(id);
    }
    @GetMapping(path = "listes")
    public List<Vendeur> liste() {
        return service.liste();
    }
    @PutMapping(path = "modifier/{id}")
    public ResponseEntity<Vendeur> modifier(@Valid @PathVariable Long id, @RequestBody Vendeur vendeur){
        return service.modifier(id, vendeur);
    }
    @PatchMapping(path = "partiel/{id}")
    public ResponseEntity<Vendeur> modifierPartiel(@Valid @PathVariable Long id, @RequestBody Map<String, Object> updates){
        return service.Partiel(id, updates);
    }
    @GetMapping(path="filtre")
    public ResponseEntity<Vendeur> filtreVendeur(@Valid @RequestParam String nom, @RequestParam String prenom){
        return service.filtreVendeur(nom, prenom);
    }
    @DeleteMapping(path = "supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        try{
            service.supprimer(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
