package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.Administrateur;
import com.source.epoissonnerie.entites.Client;
import com.source.epoissonnerie.repository.ClientRepository;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Builder
public class ClientService {
    public final ClientRepository repository;
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
            return clientOptional.map(ResponseEntity::ok).orElseThrow(()->new IllegalStateException("Le client est introuvable !"));
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
        Optional<Client> clientOptional = repository.findById(id);
        try {
           clientOptional.ifPresent(repository::delete);
        }
        catch (Exception e) {
            ResponseEntity.status(500).build();
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<List<Client>>filtreClient(String nom, String prenom){
        Optional<List<Client>> clientOptional = repository.findByNomAndPrenom(nom,prenom);
       return clientOptional
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound()
                       .build());
    }
}
