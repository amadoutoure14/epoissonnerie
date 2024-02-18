package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entity.Client;
import com.source.epoissonnerie.service.ClientService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Builder
@RequestMapping(value = "/client")
public class ClientController {
    public final ClientService service;
    @PostMapping(value = "/ajouter")
    public ResponseEntity<Client> ajouter(@Valid @RequestBody Client client){
        return service.ajouter(client);
    }
    
}
