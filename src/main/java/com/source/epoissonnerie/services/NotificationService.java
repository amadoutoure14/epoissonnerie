package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@AllArgsConstructor
public class NotificationService {
   JavaMailSender Sender;

    public void envoyer(Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("amadouit223@gmail.com");
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject("E-POISSONNERIE CODE");
        message.setSentDate(Date.from(Instant.now()));
        String texte = String.format(
                "Bonjour %s, votre code d'activation est %s; Á bientôt. Ce message a été envoyer à %s et expirera dans 10 minutes!",
                validation.getUtilisateur().getNom_complet(),
                validation.getCode(),
                validation.getCreation()
                );
        message.setText(texte);
        Sender.send(message);
    }
}
