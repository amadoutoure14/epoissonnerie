package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Client;
import com.source.epoissonnerie.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/client",consumes = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    private ClientService clientService;
    @PostMapping
    ResponseEntity<?> nouveauClient(@RequestBody Client client){
        return clientService.nouveauClient(client);
    }
    @GetMapping("/{id}")
    public EntityModel<Client> un(@PathVariable Long id) {
        return clientService.un(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Client>> liste() {
        return clientService.liste();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id,@RequestBody Client client) {
        return clientService.modifier(id,client);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> modifierPartiel(@PathVariable Long id,@RequestBody Map<String, Object> client) {
        return clientService.modifierPartiel(id,client);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return clientService.supprimer(id);
    }
}
