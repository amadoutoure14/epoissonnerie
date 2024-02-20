package com.source.epoissonnerie.service;

import com.source.epoissonnerie.entity.Poisson;
import com.source.epoissonnerie.repository.PoissonRepository;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Builder
public class PoissonService {
    public final PoissonRepository repository;
    public ResponseEntity<Poisson> ajouter(Poisson poisson){
        Poisson save = repository.save(poisson);
        try{
            return ResponseEntity.ok(save);
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }
    public ResponseEntity<Poisson> unPoisson(Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    public List<Poisson> liste(){
        return repository.findAll();
    }
    public ResponseEntity<Poisson> modifier(Long id, Poisson poisson){
        Optional<Poisson> optional  = repository.findById(id);
      try {
          optional.map(
                  maj -> {
                      maj.setNom(poisson.getNom());
                      maj.setCollection(poisson.getCollection());
                      maj.setDescription(poisson.getDescription());
                      maj.setType(poisson.getType());
                      maj.setPublier(poisson.isPublier());
                      maj.setCommentaire(poisson.getCommentaire());
                      maj.setEvaluation(poisson.getEvaluation());
                      maj.setQuantite(poisson.getQuantite());
                      maj.setPrixUnitaire(poisson.getPrixUnitaire());
                      Poisson save = repository.save(maj);
                      return ResponseEntity.ok(save);
                  }
          );
          return ResponseEntity.notFound().build();
      }catch (Exception e){
          return ResponseEntity.status(500).build();
      }
    }
    public ResponseEntity<Poisson> partiel(Long id, Map<String, Object> poisson){
        Optional<Poisson> poissonOptional = repository.findById(id);
        try {
            poissonOptional.map(maj ->{
                poisson.forEach(
                        (key, value) ->{
                            switch(key){
                                case "nom":maj.setNom((String) value);
                                    break;
                                case "quantite":maj.setQuantite((Integer) value);
                                    break;
                                case "prixUnitaire":maj.setPrixUnitaire((Double) value);
                                    break;
                                case "publier":maj.setPublier((Boolean) value);
                                    break;
                            }
                        }
                );
                return repository.save(maj);
            });
            return ResponseEntity.ok(poissonOptional.orElseThrow(()->new IllegalStateException("Poisson est introuvable!")));
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }
    public ResponseEntity<Void> supprimer(Long id) {
        Optional<Poisson> optional = repository.findById(id);
        try {
            optional.ifPresent(repository::delete);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    public ResponseEntity<List<Poisson>> filtrePoisson(String nom) {
       try {
           return repository.findByNom(nom)
                   .map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
       }catch (Exception e){
           return ResponseEntity.status(500).build();
       }
    }
}
