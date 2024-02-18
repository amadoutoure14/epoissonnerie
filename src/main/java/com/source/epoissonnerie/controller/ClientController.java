package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entity.Client;
import com.source.epoissonnerie.service.ClientService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Builder
@RequestMapping(value = "/client")
public class ClientController {
    public final ClientService service;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/ajouter")
    public Client ajouter(@Valid @RequestBody Client client){
        return service.ajouter(client);
    }
    
}
