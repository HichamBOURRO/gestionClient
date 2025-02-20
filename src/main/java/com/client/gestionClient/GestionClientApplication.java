package com.client.gestionClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.client.gestionClient.application.controllers",
		"com.client.gestionClient.infrastructure.adapter.persistence.adapters",
		"com.client.gestionClient.infrastructure.config",
		"com.client.gestionClient.application.service",
		"com.client.gestionClient.infrastructure.adapter.persistence"
}
)
public class GestionClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionClientApplication.class, args);
	}

}
