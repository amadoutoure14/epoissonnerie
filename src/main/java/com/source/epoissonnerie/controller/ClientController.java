package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entity.Client;
import com.source.epoissonnerie.service.ClientService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Builder
@RequestMapping(value = "/client")
public class ClientController {
    public final ClientService service;
    @PostMapping(value = "/ajouter")
    public ResponseEntity<Client> ajouter(@Valid @RequestBody Client client){
        return service.ajouter(client);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> leClient(@PathVariable Long id){
        return service.leClient(id);
    }
    @PutMapping(value = "/modifier/{id}")
    public ResponseEntity<Client> modifier(@Valid @PathVariable Long id, @Valid @RequestBody Client client){
        return service.modifier(id, client);
    }
    @PatchMapping(value = "/partiel/{id}")
    public ResponseEntity<Client> partiel(@Valid @PathVariable Long id, @RequestBody Map<String,Object> client){
        return service.partiel(id, client);
    }
    @DeleteMapping(value = "/supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        return service.supprimer(id);
    }
    
}
