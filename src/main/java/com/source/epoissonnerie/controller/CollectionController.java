package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Collection;
import com.source.epoissonnerie.services.CollectionService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path ="collection",consumes = MediaType.APPLICATION_JSON_VALUE)
@Builder
public class CollectionController {
    public final CollectionService service;
    @PostMapping(path = "ajouter")
    public ResponseEntity<Collection> ajouter(@Valid @RequestBody Collection collection){
        return service.ajouter(collection);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<Collection> uneCollection(@PathVariable Long id){
        return service.uneCollection(id);
    }
    @GetMapping(path = "filtre")
    public ResponseEntity<Optional<List<Collection>>> filtre(@RequestParam String nom){
        return service.filtre(nom);
    }
    @GetMapping(path = "liste")
    public ResponseEntity<List<Collection>> liste(){
        return service.liste();
    }
    @PutMapping(path = "modifier/{id}")
    ResponseEntity<Collection> modifier(@PathVariable Long id, @RequestBody Collection collection){
        return service.modifier(id,collection);
    }
    @PatchMapping(path = "partiel/{id}")
    public ResponseEntity<Collection> partiel(@PathVariable Long id, @RequestBody Map<String, Object> collection){
        return service.partiel(id,collection);
    }
    @DeleteMapping(path="supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        return service.supprimer(id);
    }
}
