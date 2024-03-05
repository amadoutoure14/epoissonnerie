package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entites.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratreurRepository extends JpaRepository<Administrateur, Long> {

}
