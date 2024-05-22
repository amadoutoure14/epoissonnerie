package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.VendeurPoisson;
import com.source.epoissonnerie.services.VendeurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/vendeur",consumes = MediaType.APPLICATION_JSON_VALUE)
public class VendeurController {

    final private VendeurService vendeurService;
    @PostMapping
    public ResponseEntity<VendeurPoisson> nouveauVendeur(@Valid @RequestBody VendeurPoisson vendeur){
        return vendeurService.nouveauVendeur(vendeur);
    }
}
