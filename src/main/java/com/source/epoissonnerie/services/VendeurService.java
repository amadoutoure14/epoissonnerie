package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.Administrateur;
import com.source.epoissonnerie.entites.Vendeur;
import com.source.epoissonnerie.repository.VendeurRepository;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

@Service
@Builder
public class VendeurService {
    private final VendeurRepository repository;
    private final PoissonService poissonService;
    public ResponseEntity<Vendeur> unVendeur(Long id) {
        Vendeur vendeur = repository.findById(id).orElseThrow(()->new IllegalStateException("Le vendeur est introuvable !"));
        return ResponseEntity.ok(vendeur);
    }
    public List<Vendeur> liste(){
        return repository.findAll();
    }

    public ResponseEntity<Vendeur> modifier(Long id, Vendeur vendeur){
        Optional<Vendeur> optional  = repository.findById(id);
        optional.map(
                maj -> {
                    maj.setTel(vendeur.getTel());
                    maj.setNom(vendeur.getNom());
                    maj.setPrenom(vendeur.getPrenom());
                    maj.setMdp(vendeur.getMdp());
                   try {
                       return repository.save(maj);
                   }catch (Exception e) {
                       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                   }
                }
        );
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Vendeur> Partiel(Long id, Map<String, Object> vendeur){
        Optional<Vendeur> optionalVendeur = repository.findById(id);
        optionalVendeur.map(maj -> {
                    vendeur.forEach(
                            (key,value) -> {
                                switch (key){
                                    case "tel":
                                        maj.setTel((Integer) value);
                                        break;
                                    case "nom":
                                        maj.setNom((String) value);
                                        break;
                                    case "prenom":
                                        maj.setPrenom((String) value);
                                        break;
                                    case "mdp":
                                        maj.setMdp((String) value);
                                        break;
                                }
                            }
                    );
            try {
                return ResponseEntity.ok(repository.save(maj));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        });
        return ResponseEntity.notFound().build();
    }
    public void supprimer(Long id){
        Optional<Vendeur> vendeur = repository.findById(id);
        vendeur.ifPresent(repository::delete);
    }
    public ResponseEntity<Vendeur> filtreVendeur(String nom, String prenom) {
        try {
            if (nom == null || prenom == null) {
                return ResponseEntity.badRequest().build();
            }
            return repository.findByNomAndPrenom(nom, prenom)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> new IllegalStateException("le vendeur n'existe pas!"));

        } catch (Exception e) {
            LOGGER.error("une erreur s'est produite lors du filtrage des vendeurs", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
