package com.client.gestionClient.infrastructure.exceptionhandler;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}