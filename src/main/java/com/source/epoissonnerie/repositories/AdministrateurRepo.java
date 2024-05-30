package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Administrateur;
import com.source.epoissonnerie.entites.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepo extends JpaRepository<Administrateur, Long> {
}
