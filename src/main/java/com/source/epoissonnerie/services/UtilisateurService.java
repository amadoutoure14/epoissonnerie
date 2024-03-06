package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.Role;
import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.entites.Validation;
import com.source.epoissonnerie.repository.UtilisateurRepository;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@Builder
public class UtilisateurService {
    private final UtilisateurRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ValidationService validationService;
    public ResponseEntity<Utilisateur> inscription(Utilisateur utilisateur){
        String MdpCrypte = this.passwordEncoder.encode(utilisateur.getMdp());//On prend et crypte le mot de passe du client
        utilisateur.setMdp(MdpCrypte);//On remplace le mot de passe du client par le mot de passe crypte
        Role roleUtilisateur = new Role();//On crée le role de l'utilisateur
        utilisateur = this.repository.save(utilisateur);//On enregistre l'utilisateur dans la base de données
        this.validationService.enregistrer(utilisateur);//On enregistre le code de validation dans la base de données
        return ResponseEntity.ok(utilisateur);// On retourne une reponse http 200 et l'utilisateur
    }
    public void activation(Map<String,String> activation){
        Validation validation = this.validationService.lireCode(activation.get("code"));
        if (Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("Votre code a expiré");
        }
        Utilisateur utilisateurActiver = this.repository.findById(validation.getUtilisateur().getId()).orElseThrow(() -> new RuntimeException("L'utilisateur est introuvable!"));
        utilisateurActiver.setActif(true);
        this.repository.save(utilisateurActiver);
    }
}
