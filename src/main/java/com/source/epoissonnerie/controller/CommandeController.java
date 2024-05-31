package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Commande;
import com.source.epoissonnerie.services.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/commande",consumes = MediaType.APPLICATION_JSON_VALUE)
public class CommandeController {
    final private CommandeService commandeService;
    @PostMapping
    ResponseEntity<?> nouvelle(@RequestBody Commande commande){
        return commandeService.nouvelle(commande);
    }
    @GetMapping("/{id}")
    public EntityModel<Commande> une(@PathVariable Long id) {
        return commandeService.une(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Commande>> liste() {
        return commandeService.liste();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id,@RequestBody Commande commande) {
        return commandeService.modifier(id,commande);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> modifierPartiel(@PathVariable Long id,@RequestBody Map<String, Object> commande) {
        return commandeService.modifierPartiel(id,commande);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return commandeService.supprimer(id);
    }

}
