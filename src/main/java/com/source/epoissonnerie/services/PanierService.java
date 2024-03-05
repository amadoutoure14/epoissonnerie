package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.Panier;
import com.source.epoissonnerie.repository.PanierRepository;
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
public class PanierService {
    public final PanierRepository repository;
    public ResponseEntity<Panier> ajouter(Panier panier){
       try {
           return ResponseEntity.ok(repository.save(panier));
       }catch (DataIntegrityViolationException | IllegalArgumentException ex) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .build();
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    public ResponseEntity<Panier> lePanier(Long id) {
       try {
           return repository.findById(id)
                   .map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
       }catch (DataIntegrityViolationException | IllegalArgumentException ex) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .build();
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    public ResponseEntity<Void> supprimer(Long id) {
       try {
           repository.deleteById(id);
           return ResponseEntity.noContent().build();
       }catch (DataIntegrityViolationException | IllegalArgumentException ex) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .build();
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    public ResponseEntity<List<Panier>> liste(){
       try {
           return ResponseEntity.ok(repository.findAll());
       }catch (DataIntegrityViolationException | IllegalArgumentException ex) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .build();
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    public ResponseEntity<Panier> modifier(Long id, Panier panier){
        Optional<Panier> optionalPanier = repository.findById(id);
       try {
           optionalPanier.map(
                   maj -> {
                       maj.setNom(panier.getNom());
                       maj.setDescription(panier.getDescription());
                       maj.setQuantite(panier.getQuantite());
                       maj.setPrix_total(panier.getPrix_total());
                       return repository.save(maj);
                   });
           return ResponseEntity.ok(optionalPanier.orElseThrow(()->new IllegalStateException("Le panier est introuvable !")));
       }catch (DataIntegrityViolationException | IllegalArgumentException ex) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .build();
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    public ResponseEntity<Panier> partiel(Long id, Map<String, Object> panier) {
        Optional<Panier> optionalPanier = repository.findById(id);
        try {
            optionalPanier.map(
                    maj ->{
                        panier.forEach(
                                (key, value) ->{
                                    switch (key) {
                                        case "nom":
                                            maj.setNom((String) value);
                                            break;
                                        case "description":
                                            maj.setDescription((String) value);
                                            break;
                                        case "quantite":
                                            maj.setQuantite((Integer) value);
                                            break;
                                        case "prix_total":
                                            maj.setPrix_total((Double) value);
                                            break;
                                        default:
                                            break;
                                    }
                                }
                        );
                        return ResponseEntity.ok(repository.save(maj));
                    }
            );
            return ResponseEntity.notFound().build();
        }catch (DataIntegrityViolationException | IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
