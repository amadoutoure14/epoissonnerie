package com.source.epoissonnerie.service;

import com.source.epoissonnerie.entity.Administrateur;
import com.source.epoissonnerie.entity.Vendeur;
import com.source.epoissonnerie.repository.VendeurRepository;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Builder
public class VendeurService {
    public final VendeurRepository repository;
    public Vendeur  ajouter(Vendeur vendeur){
        Administrateur administrateur = new Administrateur();
        administrateur.setId(1L);
        vendeur.setAdministrateur(administrateur);
        return repository.save(vendeur);
    }
    public ResponseEntity<Vendeur> unVendeur(Long id) {
        Vendeur vendeur = repository.findById(id).orElseThrow(()->new IllegalStateException("Le vendeur est introuvable !"));
        return ResponseEntity.ok(vendeur);
    }
    public List<Vendeur> liste(){
        return repository.findAll();
    }

    public Vendeur modifier(Long id, Vendeur vendeur){
        Optional<Vendeur> optional  = repository.findById(id);
        optional.map(
                maj -> {
                    maj.setTel(vendeur.getTel());
                    maj.setNom(vendeur.getNom());
                    maj.setPrenom(vendeur.getPrenom());
                    maj.setMdp(vendeur.getMdp());
                    return repository.save(maj);
                }
        );
        return optional.orElseThrow(()->new IllegalStateException("Le vendeur est introuvable !"));
    }

    public Vendeur Partiel(Long id, Map<String, Object> vendeur){
        Optional<Vendeur> optionalVendeur = repository.findById(id);
        optionalVendeur.map(
                maj -> {
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
                    return repository.save(maj);
                }
        );
        return optionalVendeur.orElseThrow(()->new IllegalStateException("Le vendeur est introuvable !"));
    }
    public void supprimer(Long id){
        Optional<Vendeur> vendeur = repository.findById(id);
        vendeur.ifPresent(repository::delete);
    }
}
