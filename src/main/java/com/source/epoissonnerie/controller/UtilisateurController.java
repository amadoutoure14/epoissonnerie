package com.source.epoissonnerie.controller;

import com.source.epoissonnerie.config.JwtService;
import com.source.epoissonnerie.dto.AuthentificationDTO;
import com.source.epoissonnerie.dto.UtilisateurDTO;
import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.services.UtilisateurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/utilisateur",consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurController {
    final private  UtilisateurService service;
    final private AuthenticationManager authentificationManager;
    final private JwtService jwtService;
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
        if(authenticate.isAuthenticated()){
         return jwtService.generate(authentificationDTO.username());
        }
        return null;
    }
    @GetMapping("/{id}")
    public EntityModel<UtilisateurDTO> un(@PathVariable Long id) {
        return service.un(id);
    }

    public CollectionModel<EntityModel<Utilisateur>> liste() {
        return service.tout();
    }
}
