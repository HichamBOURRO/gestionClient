package com.client.gestionClient.application.service;

import com.client.gestionClient.domain.model.LivraisonModel;
import com.client.gestionClient.domain.port.input.LivraisonUseCase;
import com.client.gestionClient.domain.port.output.ClientRepositoryPort;
import com.client.gestionClient.domain.port.output.LivraisonRepositoryPort;
import com.client.gestionClient.infrastructure.adapter.persistence.adapters.LivraisonAdapter;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.ClientEntity;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.LivraisonEntity;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.TypeLivraisonEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LivraisonService implements LivraisonUseCase {

    private final LivraisonAdapter livraisonAdapter;
    private final LivraisonRepositoryPort livraisonRepositoryPort;
    private final ClientRepositoryPort clientRepositoryPort;

    public LivraisonService(LivraisonAdapter livraisonAdapter, LivraisonRepositoryPort livraisonRepositoryPort, ClientRepositoryPort clientRepositoryPort) {
        this.livraisonAdapter = livraisonAdapter;
        this.livraisonRepositoryPort = livraisonRepositoryPort;
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public LivraisonModel getLivraisonByIdAndEmail(Long id, String email) {
        LivraisonEntity livraisonEntity= livraisonRepositoryPort.getLivraisonById(id);
        if (livraisonEntity==null){
            return null;
        }
        else{
            ClientEntity clientEntity= clientRepositoryPort.getClientById(livraisonEntity.getClient().getIdClient());
            if (clientEntity==null){
                return null;
            }
            else{
                if (clientEntity.getEmail().equals(email)){
                    return livraisonAdapter.toModel(livraisonEntity);
                }
                else{
                    return null;
                }
            }
        }
    }

    @Override
    public LivraisonModel addLivraison(LivraisonModel livraison) {
        LivraisonEntity livraisonEntity=livraisonAdapter.toEntity(livraison);
        ClientEntity client=clientRepositoryPort.getClientByEmail(livraisonEntity.getEmail());
        TypeLivraisonEntity typeLivraisonEntity=livraisonRepositoryPort.getTypeLivraison(livraisonEntity.getType());
        if (client==null || typeLivraisonEntity==null){
            return null;
        }
        if (livraisonEntity.getDateCreation()==null){
            livraisonEntity.setDateCreation(LocalDateTime.now());
        }
        livraisonEntity.setTypeLivraison(typeLivraisonEntity);
        livraisonEntity.setClient(client);
        return livraisonAdapter.toModel(livraisonRepositoryPort.addLivraison(livraisonEntity));
    }

    @Override
    public void deleteLivraison(Long id) {
        livraisonRepositoryPort.deleteLivraison(id);
    }

    @Override
    public LivraisonModel getLivraisonById(Long id) {
        return livraisonAdapter.toModel(livraisonRepositoryPort.getLivraisonById(id));
    }

    @Override
    public List<LivraisonModel> getLivraisons() {
        List<LivraisonEntity> livraisons=livraisonRepositoryPort.getLivraisons();

        if (livraisons==null || livraisons.isEmpty()){
            return null;
        }else{
            livraisons.forEach(c->{
                ClientEntity clientEntity=clientRepositoryPort.getClientById(c.getClient().getIdClient());
                TypeLivraisonEntity typeLivraisonEntity=livraisonRepositoryPort.getTypeIdLivraison(c.getTypeLivraison().getIdTypeLivraison());
                c.setEmail(clientEntity.getEmail());
                c.setType(typeLivraisonEntity.getTypeLivraison());
            });
            return livraisonAdapter.toModelList(livraisons);
        }
    }

    @Override
    public List<LivraisonModel> getLivraisonsByEmail(String email) {
        ClientEntity client=clientRepositoryPort.getClientByEmail(email);
        Long idclient=client.getIdClient();
        List<LivraisonEntity> livraisons=livraisonRepositoryPort.getLivraisonsByIdClient(idclient);
        return livraisonAdapter.toModelList(livraisons);
    }
}
