package com.client.gestionClient.domain.port.input;

import com.client.gestionClient.domain.model.ClientModel;

import java.util.List;

public interface ClientUseCase {
    public ClientModel addClient(ClientModel client);
    public void deleteClient(Long id);
    public ClientModel getClientById(Long id);
    public List<ClientModel> getClients();
}
