package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.entites.Poisson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoissonRepo extends JpaRepository<Poisson, Long> {
}
