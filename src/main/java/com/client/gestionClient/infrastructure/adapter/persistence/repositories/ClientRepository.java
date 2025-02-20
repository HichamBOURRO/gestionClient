package com.client.gestionClient.infrastructure.adapter.persistence.repositories;


import com.client.gestionClient.infrastructure.adapter.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByEmail(String email);  // Recherche d'un client par email
}
