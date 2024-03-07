package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.Evaluation;
import com.source.epoissonnerie.repository.EvaluationRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EvaluationService {
    private final EvaluationRepository repository;
    public ResponseEntity<Evaluation> ajouter(Evaluation evaluation) {
        try {
            return ResponseEntity.ok(repository.save(evaluation));
        }catch (DataIntegrityViolationException | IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<Evaluation> uneEvaluation(Long id) {
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
    public ResponseEntity<List<Evaluation>> liste(){
        try {
            return ResponseEntity.ok(repository.findAll());
        }catch (DataIntegrityViolationException | IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<Void> supprimer(Long id){
        Optional<Evaluation> optionalEvaluation = repository.findById(id);
        try {
            optionalEvaluation.ifPresent(repository::delete);
        } catch (DataIntegrityViolationException | IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(404).build();
    }
    public ResponseEntity<Evaluation> modifier(Long id, Evaluation evaluation){
        Optional<Evaluation> optionalEvaluation = repository.findById(id);
       try {
           optionalEvaluation.map(
                   maj ->{
                       maj.setNote(evaluation.getNote());
                       return ResponseEntity.ok(maj);
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
