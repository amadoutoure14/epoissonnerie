package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.entites.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepo extends JpaRepository<Commentaire, Long> {
}
