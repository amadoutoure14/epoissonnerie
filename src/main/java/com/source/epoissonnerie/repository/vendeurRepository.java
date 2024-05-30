package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface vendeurRepository extends JpaRepository<Vendeur, Long> {
}
