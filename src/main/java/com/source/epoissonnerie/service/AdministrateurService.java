package com.source.epoissonnerie.service;

import com.source.epoissonnerie.entity.Administrateur;
import com.source.epoissonnerie.entity.Client;
import com.source.epoissonnerie.entity.Vendeur;
import com.source.epoissonnerie.repository.*;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hibernate.sql.results.LoadingLogger.LOGGER;

@Service
@Builder
public class AdministrateurService {
    private final AdministratreurRepository administratreurRepository;
    private final VendeurRepository vendeurRepository;
    private final VendeurService vendeurService;
    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final CommentaireRepository commentaireRepository;
    private final EvaluationRepository evaluationRepository;
    public ResponseEntity<Administrateur> ajouter(Administrateur admin){
        try {
            return ResponseEntity.ok(administratreurRepository.save(admin));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    public  ResponseEntity<Vendeur> activerVendeur(Long id){
        Vendeur vendeur = vendeurRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le vendeur est introuvable"));
        vendeur.setActif(true);
        try {
            return ResponseEntity.ok(vendeurRepository.save(vendeur));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }
    public ResponseEntity<Vendeur> desactiverVendeur(Long id){
        Vendeur vendeur = vendeurRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le vendeur est introuvable"));
        vendeur.setActif(false);
        try{
            return ResponseEntity.ok(vendeurRepository.save(vendeur));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    public List<Vendeur> vendeurList(){
        return vendeurRepository.findAll();
    }
    public void supprimerVendeur(Long id){
        try{
            vendeurService.supprimer(id);
            ResponseEntity.noContent().build();
        }catch (Exception e){
            LOGGER.error("une erreur s'est produite lors du filtrage des vendeurs", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Client> activerClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client est introuvable"));
        client.setActif(true);
       try{
           return ResponseEntity.ok(clientRepository.save(client));
       }catch (Exception e){
           return ResponseEntity.internalServerError().build();
       }
    }
    public ResponseEntity<Client> deactiverClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Le client est introuvable"));
        client.setActif(false);
        try{
            return ResponseEntity.ok(clientRepository.save(client));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    public void supprimerClient(Long id){
        clientService.supprimer(id);
    }
    public List<Client> clientList() {
        return clientRepository.findAll();
    }

}
