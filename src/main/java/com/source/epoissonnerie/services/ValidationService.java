package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.entites.Validation;
import com.source.epoissonnerie.repository.validationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
@AllArgsConstructor
public class ValidationService {

    private final validationRepo repository;
    private final NotificationService notificationService;
    public void enregistrer(Utilisateur utilisateur){

        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);

        Instant creation = Instant.now();
        validation.setCreation(creation);

        Instant expiration = creation.plus(10,ChronoUnit.MINUTES);
        validation.setExpiration(expiration);

        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d",randomInteger);
        validation.setCode(code);

        this.repository.save(validation);
        this.notificationService.envoyer(validation);
    }
    public Validation lireCode(String code) {
        return this.repository.findByCode(code).orElseThrow(() -> new RuntimeException("Votre code est invalide"));
    }
}
