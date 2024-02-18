package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entity.Poisson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoissonRepository extends JpaRepository<Poisson, Long> {
}
