package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.services.UtilisateurService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Builder
@RequestMapping(value = "/utilisateur",consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurController {
    private final UtilisateurService service;

    @PostMapping(value = "/inscription")
    public ResponseEntity<Utilisateur> inscription(@Valid @RequestBody Utilisateur utilisateur){
        return service.inscription(utilisateur);
    }
    @PostMapping(path = "activaction")
    public void validation(@Valid @RequestBody Map<String, String> activation){
        return ;
    }
}
