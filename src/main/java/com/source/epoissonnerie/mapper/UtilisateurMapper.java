package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.UtilisateurDTO;
import com.source.epoissonnerie.entites.Utilisateur;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class UtilisateurMapper implements Function<Utilisateur, UtilisateurDTO> {
    @Override
    public UtilisateurDTO apply(Utilisateur utilisateur) {
        return new UtilisateurDTO(utilisateur.getId(), utilisateur.getNom(),utilisateur.getEmail(),utilisateur.getTel(),utilisateur.getMdp(),utilisateur.isActif(),utilisateur.getRole());
    }
}
