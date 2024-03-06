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
@RequestMapping(value = "/admin",consumes = MediaType.APPLICATION_JSON_VALUE)
public class AdministrateurController {
    public final AdministrateurService service;

    @GetMapping("/vendeurs")
    public List<Vendeur> listeVendeur(){
        return service.vendeurList();
    }

    @PatchMapping("/activerVendeur/{id}")
    public ResponseEntity<Vendeur> activerVendeur(@PathVariable Long id){
        return service.activerVendeur(id);
    }
    @PatchMapping("/desactiverVendeur/{id}")
    public ResponseEntity<Vendeur> desactiverVendeur(@PathVariable Long id){
        return service.desactiverVendeur(id);
    }
    @DeleteMapping(value = "/supprimeVendeur/{id}")
    public void supprimerVendeur(@PathVariable Long id){
         service.supprimerVendeur(id);
    }
    @PatchMapping(value = "/activerClient/{id}")
    public ResponseEntity<Client> activerClient(@PathVariable Long id){
        return service.activerClient(id);
    }
    @PatchMapping(value = "/deactiverClient/{id}")
    public ResponseEntity<Client> deactiverClient(@PathVariable Long id){
        return service.deactiverClient(id);
    }
    @PatchMapping(value = "/supprimerClient/{id}")
    public void supprimerClient(@PathVariable Long id){
        service.supprimerClient(id);
    }
    @GetMapping(value = "/clients")
    public List<Client> listeClient(){
        return service.clientList();
    }

}