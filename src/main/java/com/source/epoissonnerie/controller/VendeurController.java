package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.VendeurPoisson;
import com.source.epoissonnerie.services.VendeurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/vendeur",consumes = MediaType.APPLICATION_JSON_VALUE)
public class VendeurController {

    final private VendeurService vendeurService;
    @PostMapping("/nouveau")
    ResponseEntity<?> nouveauVendeur(@RequestBody VendeurPoisson vendeur) {
        return vendeurService.nouveauVendeur(vendeur);
    }
    @GetMapping("/id/{id}")
    public EntityModel<VendeurPoisson> un(@PathVariable int id) {
        return vendeurService.un(id);
    }
    @GetMapping("/tout")
    public CollectionModel<EntityModel<VendeurPoisson>> tout() {
        return vendeurService.tout();
    }
}
