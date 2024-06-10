package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepo extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByNom(String nom);
}
