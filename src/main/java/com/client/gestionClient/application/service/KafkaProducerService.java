package com.client.gestionClient.application.service;
import com.client.gestionClient.domain.model.ClientModel;
import com.client.gestionClient.domain.model.LivraisonModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClientEvent(ClientModel event) {
        kafkaTemplate.send("client-events", event);
        System.out.println("Client Event envoyé: " + event);
    }

    public void sendLivraisonEvent(LivraisonModel event) {
        kafkaTemplate.send("livraison-events", event);
        System.out.println("Livraison Event envoyé: " + event);
    }
}
