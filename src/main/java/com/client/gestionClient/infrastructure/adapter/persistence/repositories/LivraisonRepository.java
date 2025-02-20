package com.client.gestionClient.infrastructure.adapter.persistence.repositories;

import com.client.gestionClient.infrastructure.adapter.persistence.entities.LivraisonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivraisonRepository extends JpaRepository<LivraisonEntity, Long> {
    @Query("SELECT l FROM LivraisonEntity l WHERE l.client.idClient = :idClient")
    List<LivraisonEntity> findByIdClient(Long idClient);
}
