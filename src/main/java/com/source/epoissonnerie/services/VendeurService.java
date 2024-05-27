package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.VendeurPoisson;
import com.source.epoissonnerie.repository.VendeurRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VendeurService {

    final public VendeurRepository vendeurRepository;
    final public BCryptPasswordEncoder encoder;

    public static boolean verifierInt(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean verifierNomComplet(String nomComplet) {
        if (nomComplet == null || nomComplet.isEmpty()) {
            return false;
        }
        String[] parties = nomComplet.split(" ");
        if (parties.length != 2) {
            return false;
        }
        for (String partie : parties) {
            if (!partie.matches("[A-Za-z]{2,}")) {
                return false;
            }
        }
        return true;
    }


    public ResponseEntity<VendeurPoisson> nouveauVendeur(VendeurPoisson vendeur) {
        String tel = Integer.toString(vendeur.getTel());
        boolean telInt = verifierInt(tel);
        boolean nomComplet = verifierNomComplet(vendeur.getNomComplet());
        try {
            if (!telInt){
                throw new IllegalArgumentException("Le numero ne doit pas contenir de lettre !");
            }
            else if (!nomComplet){
                throw new IllegalArgumentException("votre nom et prénom sont requises et doivent être séparés par un espace !");
            }
            else {
                String mdpclair = this.encoder.encode(vendeur.getMdp());
                vendeur.setMdp(mdpclair);
                vendeurRepository.save(vendeur);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }
        catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }
    public ResponseEntity<List<VendeurPoisson>> lesVendeurs(){
        try {
            vendeurRepository.findAll();
            return ResponseEntity
                    .ok()
                    .build();
        }
        catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
    public ResponseEntity<VendeurPoisson> leVendeur(int id){
        try {
            vendeurRepository.findById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

}
