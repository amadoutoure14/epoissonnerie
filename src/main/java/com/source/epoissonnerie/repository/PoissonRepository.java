package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entites.Poisson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoissonRepository extends JpaRepository<Poisson, Long> {
    Optional<List<Poisson>> findByNom(String nom);
}
