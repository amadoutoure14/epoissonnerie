package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Achat;
import com.source.epoissonnerie.services.AchatService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/achat",consumes = MediaType.APPLICATION_JSON_VALUE)
public class AchatController {
    final private AchatService achatService;
    @PostMapping
    ResponseEntity<?> nouveau(@RequestBody Achat achat){
        return achatService.nouveauAchat(achat);
    }
    @GetMapping("/{id}")
    public EntityModel<Achat> un(@PathVariable Long id) {
        return achatService.un(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Achat>> liste() {
        return achatService.liste();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id,@RequestBody Achat achat) {
        return achatService.modifier(id,achat);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> modifierPartiel(@PathVariable Long id,@RequestBody Map<String, Object> achat) {
        return achatService.modifierPartiel(id,achat);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return achatService.supprimer(id);
    }

}
