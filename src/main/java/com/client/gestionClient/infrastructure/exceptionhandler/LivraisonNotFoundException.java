package com.client.gestionClient.infrastructure.exceptionhandler;

public class LivraisonNotFoundException extends RuntimeException {
    public LivraisonNotFoundException(String message) {
        super(message);
    }
}