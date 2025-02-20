package com.client.gestionClient.infrastructure.adapter.persistence.repositories;

import com.client.gestionClient.infrastructure.adapter.persistence.entities.TypeLivraisonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeLivraisonRepository extends JpaRepository<TypeLivraisonEntity, Long> {
    Optional<TypeLivraisonEntity> findByTypeLivraison(String typeLivraison);
}
