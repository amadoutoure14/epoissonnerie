package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Client;
import com.source.epoissonnerie.services.ClientService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Builder
@RequestMapping(path = "client",consumes = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    public final ClientService service;
    @PostMapping(path = "inscription")
    public ResponseEntity<Client> ajouter(@Valid @RequestBody Client client){
        return service.ajouter(client);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<Client> leClient(@PathVariable Long id){
        return service.leClient(id);
    }
    @PutMapping(path = "modifier/{id}")
    public ResponseEntity<Client> modifier(@Valid @PathVariable Long id, @Valid @RequestBody Client client){
        return service.modifier(id, client);
    }
    @PatchMapping(path = "partiel/{id}")
    public ResponseEntity<Client> partiel(@Valid @PathVariable Long id, @RequestBody Map<String,Object> client){
        return service.partiel(id, client);
    }
    @DeleteMapping(path = "supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
       try {
           return service.supprimer(id);
       }catch (Exception e){
           return ResponseEntity.internalServerError().build();
       }
    }
    @GetMapping(path = "filtre")
    public ResponseEntity<List<Client>> filtreClient(@RequestParam String nom,@RequestParam String prenom){
        return service.filtreClient(nom,prenom);
    }
}
