package com.client.gestionClient.infrastructure.config;

public enum KafkaTopics {
    CLIENT_TOPIC("client-events"),
    LIVRAISON_TOPIC("livraison-events");

    private final String topic;

    KafkaTopics(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}

