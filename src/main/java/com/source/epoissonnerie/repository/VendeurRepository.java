package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entity.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendeurRepository extends JpaRepository<Vendeur, Long> {
    Optional<Vendeur> findByNomAndPrenom(String nom,String prenom);
}
