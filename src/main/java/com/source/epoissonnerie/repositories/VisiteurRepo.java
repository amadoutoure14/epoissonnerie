package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Visiteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisiteurRepo extends JpaRepository<Visiteur, Long> {
}
