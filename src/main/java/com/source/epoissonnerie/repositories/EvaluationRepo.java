package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.entites.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepo extends JpaRepository<Evaluation, Long> {
}
