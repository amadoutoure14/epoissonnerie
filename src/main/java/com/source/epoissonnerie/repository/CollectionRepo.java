package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entites.CollectionPoisson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepo extends JpaRepository<CollectionPoisson, Long> {
}
