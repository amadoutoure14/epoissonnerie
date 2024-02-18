package com.source.epoissonnerie.controller;
import com.source.epoissonnerie.entity.Administrateur;
import com.source.epoissonnerie.entity.Client;
import com.source.epoissonnerie.entity.Vendeur;
import com.source.epoissonnerie.service.AdministrateurService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Builder
@RequestMapping(value = "/admin")
public class AdministrateurController {
    public final AdministrateurService service;
    @PostMapping(value = "/ajouter")
    @ResponseStatus(HttpStatus.CREATED)
    public Administrateur ajouter(@Valid @RequestBody Administrateur admin){
        return service.ajouter(admin);
    }
    @GetMapping("/vendeurs")
    @ResponseStatus(HttpStatus.OK)
    public List<Vendeur> listeVendeur(){
        return service.vendeurList();
    }

    @PatchMapping("/activerVendeur/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public Vendeur activerVendeur(@PathVariable Long id){
        return service.activerVendeur(id);
    }
    @ResponseStatus(HttpStatus.GONE)
    @PatchMapping("/desactiverVendeur/{id}")
    public Vendeur desactiverVendeur(@PathVariable Long id){
        return service.desactiverVendeur(id);
    }
    @ResponseStatus(HttpStatus.GONE)
    @DeleteMapping(value = "/supprimeVendeur/{id}")
    public void supprimerVendeur(@PathVariable Long id){
         service.supprimerVendeur(id);
    }
    @ResponseStatus(HttpStatus.GONE)
    @PatchMapping(value = "/activerClient/{id}")
    public Client activerClient(@PathVariable Long id){
        return service.activerClient(id);
    }
    @ResponseStatus(HttpStatus.GONE)
    @PatchMapping(value = "/deactiverClient/{id}")
    public Client deactiverClient(@PathVariable Long id){
        return service.deactiverClient(id);
    }
    @ResponseStatus(HttpStatus.GONE)
    @PatchMapping(value = "/supprimerClient/{id}")
    public void supprimerClient(@PathVariable Long id){
        service.supprimerClient(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/Clients")
    public List<Client> listeClient(){
        return service.clientList();
    }

}