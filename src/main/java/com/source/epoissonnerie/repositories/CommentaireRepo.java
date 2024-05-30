package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.entites.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepo extends JpaRepository<Commentaire, Long> {
}
