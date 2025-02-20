package com.client.gestionClient.infrastructure.adapter.persistence;

import com.client.gestionClient.domain.port.output.LivraisonRepositoryPort;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.ClientEntity;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.LivraisonEntity;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.TypeLivraisonEntity;
import com.client.gestionClient.infrastructure.adapter.persistence.repositories.LivraisonRepository;
import com.client.gestionClient.infrastructure.adapter.persistence.repositories.TypeLivraisonRepository;
import com.client.gestionClient.infrastructure.exceptionhandler.ClientNotFoundException;
import com.client.gestionClient.infrastructure.exceptionhandler.LivraisonNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivraisonPersistenceAdapter implements LivraisonRepositoryPort {

    public final LivraisonRepository livraisonRepository;
    public final TypeLivraisonRepository typeLivraisonRepository;

    public LivraisonPersistenceAdapter(LivraisonRepository livraisonRepository, TypeLivraisonRepository typeLivraisonRepository) {
        this.livraisonRepository = livraisonRepository;
        this.typeLivraisonRepository = typeLivraisonRepository;
    }


    @Override
    public LivraisonEntity addLivraison(LivraisonEntity livraison) {
        return livraisonRepository.save(livraison);
    }

    @Override
    public void deleteLivraison(Long id) {
        LivraisonEntity livraisonEntity= livraisonRepository.findById(id)
                .orElseThrow(() -> new LivraisonNotFoundException("Livraison avec l'ID " + id + " non trouvée"));
        livraisonRepository.delete(livraisonEntity);
    }

    @Override
    public LivraisonEntity getLivraisonById(Long id) {
        return livraisonRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<LivraisonEntity> getLivraisons() {
        List<LivraisonEntity> livraisonEntities=livraisonRepository.findAll();

        if (livraisonEntities.isEmpty()){
            throw new LivraisonNotFoundException("Aucune Livraison en base de données");
        }
        else{
            return livraisonEntities;
        }
    }

    @Override
    public List<LivraisonEntity> getLivraisonsByIdClient(Long idClient) {
        List<LivraisonEntity> livraisonEntities=livraisonRepository.findByIdClient(idClient);

        if (livraisonEntities.isEmpty()){
            throw new LivraisonNotFoundException("Aucune Livraison en base de données");
        }
        else{
            return livraisonEntities;
        }
    }

    @Override
    public TypeLivraisonEntity getTypeLivraison(String type) {
        return typeLivraisonRepository.findByTypeLivraison(type).orElse(null);
    }
    @Override
    public TypeLivraisonEntity getTypeIdLivraison(Long id){
        return typeLivraisonRepository.findById(id).orElse(null);
    }
}
