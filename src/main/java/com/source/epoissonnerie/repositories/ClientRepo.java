package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
}
