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
        if (vendeur.getNom_complet().isEmpty() || vendeur.getMdp().isEmpty() || telephone.isEmpty()) {
            throw new IllegalArgumentException("les informations personnelles ne sont pas valides !");
        } else if (vendeur.getMdp().startsWith("_") || vendeur.getMdp().startsWith("@")) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas commencer par '_' ou '@'");
        }
        String mdpcrypte = encoder.encode(vendeur.getMdp());
        vendeur.setMdp(mdpcrypte);

        vendeurRepository.save(vendeur);

       return ResponseEntity.status(200).build();
    }

}
