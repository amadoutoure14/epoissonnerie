package com.source.epoissonnerie.service;

import com.source.epoissonnerie.entity.Administrateur;
import com.source.epoissonnerie.entity.Client;
import com.source.epoissonnerie.repository.ClientRepository;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Builder
public class ClientService {
    public final ClientRepository repository;
    public ResponseEntity<Client>  ajouter(Client client){
        Administrateur administrateur = new Administrateur();
        administrateur.setId(1L);
        client.setAdministrateur(administrateur);
        Client save = repository.save(client);
        return ResponseEntity.ok(save);
    }
    public ResponseEntity<Client> leClient(Long id) {
        Optional<Client> client = repository.findById(id);
        try {
            return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<Client> modifier(Long id, Client client) {
        Optional<Client> clientOptional = repository.findById(id);
        try {
            clientOptional.map(maj -> {
                maj.setMdp(client.getMdp());
                maj.setNom(client.getNom());
                maj.setPrenom(client.getPrenom());
                maj.setTel(client.getTel());
                maj.setActif(client.isActif());
                return repository.save(maj);
            });
            return ResponseEntity.ok(clientOptional.get());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Client> partiel(Long id, Map<String,Object> client){
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
        return ResponseEntity.ok(clientOptional.orElseThrow(()->new IllegalStateException("Le client est introuvable !")));
    }
    public ResponseEntity<Void> supprimer(Long id) {
        Optional<Client> client = repository.findById(id);
        try {
            client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
            if (client.isPresent()) {
                repository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
