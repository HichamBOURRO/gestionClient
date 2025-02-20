package com.client.gestionClient.domain.port.output;

import com.client.gestionClient.infrastructure.adapter.persistence.entities.ClientEntity;


import java.util.List;


public interface ClientRepositoryPort {
    public ClientEntity addClient(ClientEntity client);
    public void deleteClient(Long id);
    public ClientEntity getClientById(Long id);
    public ClientEntity getClientByEmail(String email);
    public List<ClientEntity> getClients();
}
