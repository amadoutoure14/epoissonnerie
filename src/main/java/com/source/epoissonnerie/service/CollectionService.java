package com.source.epoissonnerie.service;

import com.source.epoissonnerie.entity.Collection;
import com.source.epoissonnerie.repository.CollectionRepository;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Builder
public class CollectionService {
    public final CollectionRepository repository;
    public ResponseEntity<Collection> ajouter(Collection collection) {
        try {
            return ResponseEntity.ok(repository.save(collection));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    public ResponseEntity<Collection> uneCollection(Long id) {
        return repository.findById(id)
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());
    }
    public ResponseEntity<List<Collection>> liste(){
        try {
            return ResponseEntity.ok(repository.findAll());
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }
    public ResponseEntity<Optional<List<Collection>>> filtre(String nom){
        try {
            return ResponseEntity.ok(repository.findByNom(nom));
        }
        catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }
    public ResponseEntity<Collection> modifier(Long id,Collection collection){
        Optional<Collection> optionalCollection = repository.findById(id);
        try {
            optionalCollection.map(
              maj -> {
                 maj.setDescription(collection.getDescription());
                 maj.setNom(collection.getNom());
                 maj.setPoissons(collection.getPoissons());
                 maj.setPublier(collection.isPublier());
                 return ResponseEntity.ok(repository.save(maj));
              }
            );
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Collection> partiel(Long id, Map<String, Object> collection) {
        Optional<Collection> optionalCollection = repository.findById(id);
        try {
            optionalCollection.map(
                    maj -> {
                        collection.forEach((key, value) ->{
                                    switch (key) {
                                        case "nom":
                                            maj.setNom((String) value);
                                            break;
                                        case "description":
                                            maj.setDescription((String) value);
                                            break;
                                        case "publier":
                                            maj.setPublier((Boolean) value);
                                            break;
                                    }
                                });
                        return ResponseEntity.ok(repository.save(maj));}
            );
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(404).build();
    }
    public ResponseEntity<Void> supprimer(Long id){
        Optional<Collection> optionalCollection = repository.findById(id);
        try {
            optionalCollection.ifPresent(repository::delete);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.notFound().build();
    }

}
