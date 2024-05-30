package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.entites.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepo extends JpaRepository<Panier, Long> {
}
