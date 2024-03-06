package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Poisson;
import com.source.epoissonnerie.services.PoissonService;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "poisson")
@Builder
public class PoissonController {
    public final PoissonService service;
    @PostMapping(path = "ajouter")
    public ResponseEntity<Poisson> ajouter(@RequestBody Poisson poisson){
        return service.ajouter(poisson);
    }
    @GetMapping(path = "liste")
    public ResponseEntity<List<Poisson>> liste(){
        try {
            return ResponseEntity.ok(service.liste());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping(path = "unPoisson/{id}")
    public ResponseEntity<Poisson> unPoisson(@PathVariable Long id){
        return service.unPoisson(id);
    }
    @PutMapping(path = "modifier/{id}")
    public ResponseEntity<Poisson> modifier(@PathVariable Long id, @RequestBody Poisson poisson){
        return service.modifier(id,poisson);
    }
    @PatchMapping(path = "partiel/{id}")
    public ResponseEntity<Poisson> modifierPartiel(@PathVariable Long id, @RequestBody Map<String, Object> poisson){
        return service.partiel(id, poisson);
    }
    @DeleteMapping(path = "supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        return service.supprimer(id);
    }
    @GetMapping(path = "filtre")
    public ResponseEntity<List<Poisson>> filtrePoisson(@RequestParam String nom){
        try{
            return service.filtrePoisson(nom);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
