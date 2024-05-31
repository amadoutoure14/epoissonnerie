package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Commentaire;
import com.source.epoissonnerie.services.CommentaireService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/commentaire",consumes = MediaType.APPLICATION_JSON_VALUE)
public class CommentaireController {
    final private CommentaireService commentaireService;
    @PostMapping
    ResponseEntity<?> nouveau(@RequestBody Commentaire commentaire){
        return commentaireService.nouveau(commentaire);
    }
    @GetMapping("/{id}")
    public EntityModel<Commentaire> un(@PathVariable Long id) {
        return commentaireService.un(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Commentaire>> liste() {
        return commentaireService.liste();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier( @PathVariable Long id, @RequestBody Commentaire commentaire) {
        return commentaireService.modifier(id,commentaire);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return commentaireService.supprimer(id);
    }
}
