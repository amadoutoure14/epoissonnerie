package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entites.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<Validation,Long> {
}
