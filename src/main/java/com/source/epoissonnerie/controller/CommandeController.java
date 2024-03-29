package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Commande;
import com.source.epoissonnerie.services.CommandeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/commande",consumes = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CommandeController {
    public final CommandeService service;
    @PostMapping(value ="/passer")
    public ResponseEntity<Commande> ajouter(@Valid @RequestBody Commande commande){
        return service.ajouter(commande);
    }
    @GetMapping(value ="/liste")
    public ResponseEntity<List<Commande>> liste(){
        return service.liste();
    }
    @PutMapping(value ="/modifier/{id}")
    public ResponseEntity<Commande> modifier(@PathVariable Long id, @RequestBody Commande commande){
        return service.modifier(id,commande);
    }
    @PatchMapping(value = "/partiel/{id}")
    public ResponseEntity<Commande> modifierPartiel(@PathVariable Long id, @RequestBody Map<String, Object> commande){
        return service.partiel(id, commande);
    }
    @DeleteMapping(value = "/supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        return service.supprimer(id);
    }
}