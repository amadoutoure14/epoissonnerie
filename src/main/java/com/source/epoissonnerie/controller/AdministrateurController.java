package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Administrateur;
import com.source.epoissonnerie.entites.Client;
import com.source.epoissonnerie.entites.Vendeur;
import com.source.epoissonnerie.services.AdministrateurService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Builder
@RequestMapping(path = "admin",consumes = MediaType.APPLICATION_JSON_VALUE)
public class AdministrateurController {
    public final AdministrateurService service;
    @GetMapping(path="vendeurs")
    public List<Vendeur> listeVendeur(){
        return service.vendeurList();
    }

    @PatchMapping(path="activerVendeur/{id}")
    public ResponseEntity<Vendeur> activerVendeur(@PathVariable Long id){
        return service.activerVendeur(id);
    }
    @PatchMapping(path="desactiverVendeur/{id}")
    public ResponseEntity<Vendeur> desactiverVendeur(@PathVariable Long id){
        return service.desactiverVendeur(id);
    }
    @DeleteMapping(path= "supprimeVendeur/{id}")
    public void supprimerVendeur(@PathVariable Long id){
         service.supprimerVendeur(id);
    }
    @PatchMapping(path="activerClient/{id}")
    public ResponseEntity<Client> activerClient(@PathVariable Long id){
        return service.activerClient(id);
    }
    @PatchMapping(path = "deactiverClient/{id}")
    public ResponseEntity<Client> deactiverClient(@PathVariable Long id){
        return service.deactiverClient(id);
    }
    @PatchMapping(path = "supprimerClient/{id}")
    public void supprimerClient(@PathVariable Long id){
        service.supprimerClient(id);
    }
    @GetMapping(path = "clients")
    public List<Client> listeClient(){
        return service.clientList();
    }

}