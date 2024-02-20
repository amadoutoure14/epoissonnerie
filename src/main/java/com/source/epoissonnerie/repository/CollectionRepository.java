package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Optional<List<Collection>> findByNom(String nom);
}
