package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.VendeurPoisson;
import com.source.epoissonnerie.repository.VendeurRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VendeurService {

    final public VendeurRepository vendeurRepository;
    final public BCryptPasswordEncoder encoder;

    public ResponseEntity<VendeurPoisson> nouveauVendeur(VendeurPoisson vendeur) {
        Optional<Integer> telephone = Optional.ofNullable(vendeur.getTel());
        if (vendeur.getNom_complet().isEmpty() ||vendeur.getMdp().isEmpty() || telephone.isEmpty()) {
            throw new IllegalArgumentException("Les informations personnelles ne sont pas valides ! Verifier l'espace entre le nom et le prénom!");
        }
        else if (vendeur.getNom_complet().contains(" ")){
            String mdpcrypte = encoder.encode(vendeur.getMdp());
            vendeur.setMdp(mdpcrypte);
            vendeurRepository.save(vendeur);
        }
        return ResponseEntity.status(200).build();
    }
}
