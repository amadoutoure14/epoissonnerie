package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.Role;
import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.repository.UtilisateurRepository;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class UtilisateurService {
    private final UtilisateurRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ValidationService validationService;
    public ResponseEntity<Utilisateur> inscription(Utilisateur utilisateur){
        String MdpCrypte = this.passwordEncoder.encode(utilisateur.getMdp());
        utilisateur.setMdp(MdpCrypte);
        Role roleUtilisateur = new Role();
        utilisateur = this.repository.save(utilisateur);
        this.validationService.enregistrer(utilisateur);
        return ResponseEntity.ok(utilisateur);
    }
}
