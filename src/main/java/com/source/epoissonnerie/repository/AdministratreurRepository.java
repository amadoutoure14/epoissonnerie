package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entity.Administrateur;
import com.source.epoissonnerie.entity.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratreurRepository extends JpaRepository<Administrateur, Long> {

}
