package com.source.epoissonnerie.service;

import com.source.epoissonnerie.entity.Commentaire;
import com.source.epoissonnerie.repository.CommentaireRepository;
import lombok.Builder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Builder
public class CommentaireService {
    private final CommentaireRepository repository;
    public ResponseEntity<Commentaire> ajouter(Commentaire commentaire){
        try {
            return ResponseEntity.ok(repository.save(commentaire));
        }catch (DataIntegrityViolationException | IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<Commentaire> modifier(Long id, Commentaire commentaire){
        Optional<Commentaire> commentaireOptional = repository.findById(id);
       try {
           commentaireOptional.map(
                   maj ->{
                       maj.setContenu(commentaire.getContenu());
                       maj.setPoissons(commentaire.getPoissons());
                       return ResponseEntity.ok(repository.save(maj));
                   });
           return ResponseEntity.notFound().build();
       }catch (DataIntegrityViolationException | IllegalArgumentException ex) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .build();
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    public ResponseEntity<Commentaire> partiel(Long id,Map<String,Object> commentaire) {
        Optional<Commentaire> optionalCommentaire = repository.findById(id);
        optionalCommentaire.map(
                maj -> {
                    commentaire.forEach(
                            (key, value) -> {
                                if (key.equals("contenu")) {
                                    maj.setContenu((String) value);
                                }
                            }
                    );
                    return ResponseEntity.ok(repository.save(maj));
                }
        );
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Void> supprimer(Long id) {
        Optional<Commentaire> optionalCommentaire = repository.findById(id);
        optionalCommentaire.ifPresent(repository::delete);
        return ResponseEntity.noContent().build();
    }
    public ResponseEntity<List<Commentaire>> liste(Long id) {
        Optional<Commentaire> optionalCommentaire = repository.findById(id);
        return optionalCommentaire.map(
                commentaire -> ResponseEntity.ok(repository.findAll())
        ).orElse(ResponseEntity.notFound().build());
    }
}