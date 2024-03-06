package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Vendeur;
import com.source.epoissonnerie.services.VendeurService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/vendeur",consumes = MediaType.APPLICATION_JSON_VALUE)
@Builder
public class VendeurController {
    public final VendeurService service;

    @GetMapping("/{id}")
    public ResponseEntity<Vendeur> unVendeur(@PathVariable Long id){
        return service.unVendeur(id);
    }
    @GetMapping(value = "/listes")
    public List<Vendeur> liste() {
        return service.liste();
    }
    @PutMapping(value = "/modifier/{id}")
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
    @DeleteMapping(value = "/supprimer/{id}")
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
