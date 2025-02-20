package com.client.gestionClient.infrastructure.adapter.persistence;

import com.client.gestionClient.domain.port.output.ClientRepositoryPort;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.ClientEntity;
import com.client.gestionClient.infrastructure.adapter.persistence.repositories.ClientRepository;
import com.client.gestionClient.infrastructure.config.Role;
import com.client.gestionClient.infrastructure.exceptionhandler.ClientNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientPersistenceAdapter implements ClientRepositoryPort {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ClientPersistenceAdapter(ClientRepository clientRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientEntity addClient(ClientEntity client) {
        client.setMotDePasse(passwordEncoder.encode(client.getMotDePasse()));
        client.setRole(Role.USER);
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        ClientEntity clientEntity= clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client avec l'ID " + id + " non trouvé"));
        clientRepository.delete(clientEntity);
    }

    @Override
    public ClientEntity getClientById(Long id) {
        return clientRepository.findById(id)
                .orElse(null);
    }

    @Override
    public ClientEntity getClientByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElse(null);
    }

    @Override
    public List<ClientEntity> getClients() {
        List<ClientEntity> clientEntities=clientRepository.findAll();

        if (clientEntities.isEmpty()){
            throw new ClientNotFoundException("Aucun Client en base de données");
        }
        else{
            return clientEntities;
        }
    }

//    public static void main(String[] args) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = "adminpassword"; // Ton mot de passe en clair
//        String hashedPassword = passwordEncoder.encode(password);
//        System.out.println("Mot de passe haché : " + hashedPassword);
//    }
}
