package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface utilisateurRepo extends JpaRepository<Utilisateur,Long> {
    Optional<Utilisateur> findByEmail(String username);
}
