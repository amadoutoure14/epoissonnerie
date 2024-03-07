package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.dto.AuthentificationDTO;
import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.services.UtilisateurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/utilisateur",consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurController {
    private  UtilisateurService service;
    private AuthenticationManager authentificationManager;
    @PostMapping(value = "/inscription")
    public ResponseEntity<Utilisateur> inscription(@Valid @RequestBody Utilisateur utilisateur){
        return service.inscription(utilisateur);
    }
    @PostMapping(value = "/activation")
    public void validation(@Valid @RequestBody Map<String, String> activation){
        service.activation(activation);
    }
    @PostMapping(value = "/connexion")
    public Map<String,String> connexion(@RequestBody AuthentificationDTO authentificationDTO){
        final Authentication authenticate = authentificationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password()));
        log.info("resultatr {}", authenticate.isAuthenticated());
        return null;
    }
}
