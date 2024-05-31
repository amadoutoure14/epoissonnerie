package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.config.JwtService;
import com.source.epoissonnerie.dto.AuthentificationDTO;
import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.services.UtilisateurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/utilisateur",consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurController {
    private  UtilisateurService service;
    private AuthenticationManager authentificationManager;
    private JwtService jwtService;
    @PostMapping(value = "/login")
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
        if(authenticate.isAuthenticated()){
         return this.jwtService.generate(authentificationDTO.username());
        }
        return null;
    }

    public Class<?> un() {
        return null;
    }

    public Class<?> liste() {
        return null;
    }
}
