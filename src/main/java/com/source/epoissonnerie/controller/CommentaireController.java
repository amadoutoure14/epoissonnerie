package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Commentaire;
import com.source.epoissonnerie.services.CommentaireService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/commentaire")
@Builder
public class CommentaireController {
    public final CommentaireService service;
    @PostMapping(value = "/ajouter")
    public ResponseEntity<Commentaire> ajouter(@Valid @RequestBody Commentaire commentaire){
        return service.ajouter(commentaire);
    }
    @GetMapping(value = "/liste")
    public ResponseEntity<List<Commentaire>> liste(){
        return service.liste();
    }
    @PutMapping(value = "/modifier/{id}")
    public ResponseEntity<Commentaire> modifier(@Valid @PathVariable Long id,@RequestBody Commentaire commentaire){
        return service.modifier(id, commentaire);
    }
    @PatchMapping(value = "/partiel/{id}")
    public ResponseEntity<Commentaire> partiel(@PathVariable Long id,@RequestBody Map<String,Object> commentaire){
        return service.partiel(id, commentaire);
    }
    @DeleteMapping(value = "/supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        return service.supprimer(id);
    }
}
