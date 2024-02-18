package com.source.epoissonnerie.service;

import com.source.epoissonnerie.entity.Administrateur;
import com.source.epoissonnerie.entity.Client;
import com.source.epoissonnerie.repository.ClientRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Builder
public class ClientService {
    public final ClientRepository repository;
    public Client ajouter(Client client){
        Administrateur administrateur = new Administrateur();
        administrateur.setId(1L);
        client.setAdministrateur(administrateur);
        return repository.save(client);
    }
    public Client leClient(Long id) {
        return repository.findById(id).orElseThrow(()->new IllegalStateException("Le client est introuvable !"));
    }
    public Client modifier(Long id, Client client) {
        Optional<Client> clientOptional = repository.findById(id);
        clientOptional.map(
                maj -> {
                   maj.setMdp(client.getMdp());
                   maj.setNom(client.getNom());
                   maj.setPrenom(client.getPrenom());
                   maj.setTel(client.getTel());
                   maj.setActif(client.isActif());
                   return repository.save(maj);
                }
        );
        return clientOptional.orElseThrow(()->new IllegalStateException("Le client est introuvable !"));
    }
    public Client partiel(Long id, Map<String,Object> client){
        Optional<Client> clientOptional = repository.findById(id);
        clientOptional.map(
                maj -> {
                    client.forEach(
                            (key, value) -> {
                                switch (key){
                                    case "nom":
                                        maj.setNom((String) value);
                                        break;
                                    case "prenom":
                                        maj.setPrenom((String) value);
                                        break;
                                    case "tel":
                                        maj.setTel((Integer) value);
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
        return clientOptional.orElseThrow(()->new IllegalStateException("Le client est introuvable !"));
    }
    public void supprimer(Long id){
        Optional<Client> client = repository.findById(id);
        client.ifPresent(repository::delete);
    }
}
