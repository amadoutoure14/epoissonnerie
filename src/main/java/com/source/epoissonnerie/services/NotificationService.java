package com.source.epoissonnerie.services;

import com.source.epoissonnerie.entites.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class NotificationService {
   JavaMailSender Sender;

   Environment env;

    public void envoyer(Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject("E-POISSONNERIE CODE");
        String texte = String.format(
                "Bonjour  %s, <br/> votre code d'activation est %s; Á bientôt",
                validation.getUtilisateur().getNom(),
                validation.getCode()
                );
        message.setText(texte);
        Sender.send(message);
    }
}
