package com.client.gestionClient.application.service;
import com.client.gestionClient.domain.model.ClientModel;
import com.client.gestionClient.domain.model.LivraisonModel;
import com.client.gestionClient.domain.port.input.ClientUseCase;
import com.client.gestionClient.domain.port.input.LivraisonUseCase;
import com.client.gestionClient.infrastructure.config.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {


    @Autowired(required=true)
    LivraisonUseCase livraisonUseCase;

    @Autowired(required=true)
    ClientUseCase clientUseCase;

    @KafkaListener(topics = "client-events", groupId = "mon_groupe")
    public void consumeClientEvent(ClientModel event) {
        System.out.println("Client Event reçu: " + event);
        if (OperationType.ADD==OperationType.valueOf(event.getTypeOp())) {
            clientUseCase.addClient(event);
        } else if (OperationType.DELETE==OperationType.valueOf(event.getTypeOp())) {
            clientUseCase.deleteClient(event.getIdClient());
        }
    }

    @KafkaListener(topics = "livraison-events", groupId = "mon_groupe")
    public void consumeLivraisonEvent(LivraisonModel event) {
        System.out.println("Livraison Event reçu: " + event);
        if (OperationType.ADD==OperationType.valueOf(event.getTypeOp())) {
            livraisonUseCase.addLivraison(event);
        } else if (OperationType.DELETE==OperationType.valueOf(event.getTypeOp())) {
            livraisonUseCase.deleteLivraison(event.getIdLivraison());
        }
    }
}

