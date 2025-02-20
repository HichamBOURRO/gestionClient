package com.client.gestionClient.domain.port.output;

import com.client.gestionClient.infrastructure.adapter.persistence.entities.LivraisonEntity;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.TypeLivraisonEntity;

import java.util.List;

public interface LivraisonRepositoryPort {
    public LivraisonEntity addLivraison(LivraisonEntity livraison);
    public void deleteLivraison(Long id);
    public LivraisonEntity getLivraisonById(Long id);
    public List<LivraisonEntity> getLivraisons();
    public List<LivraisonEntity> getLivraisonsByIdClient(Long idClient);
    public TypeLivraisonEntity getTypeLivraison(String id);
    public TypeLivraisonEntity getTypeIdLivraison(Long id);
}
