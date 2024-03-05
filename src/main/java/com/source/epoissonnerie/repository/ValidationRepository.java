package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entites.Validation;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidationRepository extends JpaRepository<Validation,Long> {
    Optional<Validation> findByCode(String code);
}
