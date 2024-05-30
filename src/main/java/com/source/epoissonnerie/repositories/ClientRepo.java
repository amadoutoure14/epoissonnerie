package com.source.epoissonnerie.repositories;

import com.source.epoissonnerie.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
