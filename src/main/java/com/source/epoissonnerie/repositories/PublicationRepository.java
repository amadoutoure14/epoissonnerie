package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    Optional<Publication> deleteById(Publication publication);
}
