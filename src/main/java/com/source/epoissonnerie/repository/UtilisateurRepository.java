package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entites.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
   Optional<Utilisateur> findByEmail(String email);
}
