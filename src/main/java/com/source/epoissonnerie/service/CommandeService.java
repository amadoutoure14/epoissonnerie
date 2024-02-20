package com.source.epoissonnerie.service;

import com.source.epoissonnerie.entity.Commande;
import com.source.epoissonnerie.entity.Poisson;
import com.source.epoissonnerie.repository.CommandeRepository;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Builder
public class CommandeService {
    private final CommandeRepository repository;
    public ResponseEntity<Commande> ajouter(Commande commande) {
        return ResponseEntity.ok(repository.save(commande));
    }
    public ResponseEntity<Commande> unCommande(Long id) {
        return repository.findById(id)
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());
    }
    public ResponseEntity<List<Commande>> liste(){
        try {
            return ResponseEntity.ok(repository.findAll());
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }
    public ResponseEntity<List<Commande>> filtre(String nom){
        try {
            return ResponseEntity.ok(repository.findByNom(nom));
        }
        catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }
    public ResponseEntity<Commande> modifier(Long id,Commande commande){
        Optional<Commande> optionalCommande = repository.findById(id);
        try {
            optionalCommande.map(
                    maj -> {
                        maj.setPoissons(commande.getPoissons());
                        maj.setPrix_total(commande.getPrix_total());
                        return ResponseEntity.ok(repository.save(maj));
                    }
            );
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Void> supprimer(Long id){
        Optional<Commande> optionalCommande = repository.findById(id);
        try {
            optionalCommande.ifPresent(repository::delete);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Commande> partiel(Long id, Map<String, Object> commande){
        Optional<Commande> optionalCommande = repository.findById(id);
        try {
            optionalCommande.map(
                    maj -> {
                        commande.forEach((key, value) ->{
                            switch (key) {
                                case "poissons":
                                    maj.setPoissons((List<Poisson>) value);
                                    break;
                                case "prix_total":
                                    maj.setPrix_total((Double) value);
                                    break;
                                default:
                                    throw new IllegalStateException("Unexpected value: " + key);
                            }
                        });
                        return ResponseEntity.ok(repository.save(maj));
                    }
            );
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(404).build();
    }
}
