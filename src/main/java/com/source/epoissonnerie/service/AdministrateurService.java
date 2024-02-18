package com.source.epoissonnerie.service;

import com.source.epoissonnerie.entity.Administrateur;
import com.source.epoissonnerie.entity.Client;
import com.source.epoissonnerie.entity.Vendeur;
import com.source.epoissonnerie.repository.*;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public Administrateur ajouter(Administrateur admin){
        return administratreurRepository.save(admin);
    }

    public Vendeur activerVendeur(Long id){
        Vendeur vendeur = vendeurRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le vendeur est introuvable"));
        vendeur.setActif(true);
        return vendeurRepository.save(vendeur);

    }
    public Vendeur desactiverVendeur(Long id){
        Vendeur vendeur = vendeurRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le vendeur est introuvable"));
        vendeur.setActif(false);
        return vendeurRepository.save(vendeur);
    }
    public List<Vendeur> vendeurList(){
        return vendeurRepository.findAll();
    }
    public void supprimerVendeur(Long id){
        vendeurService.supprimer(id);
    }
    public Client activerClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client est introuvable"));
        client.setActif(true);
        return clientRepository.save(client);
    }
    public Client deactiverClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Le client est introuvable"));
        client.setActif(false);
        return clientRepository.save(client);
    }
    public void supprimerClient(Long id){
        clientService.supprimer(id);
    }
    public List<Client> clientList() {
        return clientRepository.findAll();
    }
}
