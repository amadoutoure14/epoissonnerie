/*
package com.source.epoissonnerie.config;

import com.source.epoissonnerie.entites.Administrateur;
import com.source.epoissonnerie.repository.AdministrateurRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class AdministrateurPrechargeur {
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(AdministrateurPrechargeur.class);
    @Bean
    CommandLineRunner initDatabase(AdministrateurRepository repository) {

        String nom_complet = passwordEncoder.encode("Amadou Touré");
        String email = passwordEncoder.encode("amadouit223@gmail.com");
        String mdp = passwordEncoder.encode("Kabako223");
        Administrateur AdministrateurCrypter = new Administrateur(null,nom_complet,99314045,email,mdp);

        return args -> {
            log.info("Préchargement de {}", repository.save(AdministrateurCrypter));
        };
    }
}*/
