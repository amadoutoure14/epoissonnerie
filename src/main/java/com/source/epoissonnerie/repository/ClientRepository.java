package com.source.epoissonnerie.repository;

import com.source.epoissonnerie.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {
}
