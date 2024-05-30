package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Achat;
import com.source.epoissonnerie.entites.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchatRepo extends JpaRepository<Achat, Long> {
}
