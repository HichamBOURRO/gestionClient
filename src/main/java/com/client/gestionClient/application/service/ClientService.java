package com.client.gestionClient.application.service;

import com.client.gestionClient.domain.model.ClientModel;
import com.client.gestionClient.domain.port.input.ClientUseCase;
import com.client.gestionClient.domain.port.output.ClientRepositoryPort;
import com.client.gestionClient.infrastructure.adapter.persistence.adapters.ClientAdapter;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.ClientEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements ClientUseCase{

    private final ClientRepositoryPort clientRepositoryPort;
    private final ClientAdapter clientAdapter;

    @Autowired
    public ClientService(ClientRepositoryPort clientRepositoryPort, ClientAdapter clientAdapter) {
        this.clientRepositoryPort = clientRepositoryPort;
        this.clientAdapter = clientAdapter;
    }



    @Override
    public ClientModel addClient(ClientModel client) {
        ClientEntity clientEntity=clientAdapter.toEntity(client);
        return clientAdapter.toModel(clientRepositoryPort.addClient(clientEntity));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepositoryPort.deleteClient(id);
    }

    @Override
    public ClientModel getClientById(Long id) {
        return clientAdapter.toModel(clientRepositoryPort.getClientById(id));
    }

    @Override
    public List<ClientModel> getClients() {
        List<ClientEntity> clientEntities=clientRepositoryPort.getClients();
        if (clientEntities==null || clientEntities.isEmpty()){
            return null;
        }else{
            return clientAdapter.toModelList(clientRepositoryPort.getClients());
        }
    }
}