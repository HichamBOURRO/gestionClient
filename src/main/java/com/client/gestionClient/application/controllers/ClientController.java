package com.client.gestionClient.application.controllers;

import com.client.gestionClient.application.service.KafkaProducerService;
import com.client.gestionClient.domain.model.ClientModel;
import com.client.gestionClient.domain.port.input.ClientUseCase;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired(required=true)
    ClientUseCase clientUseCase;

    private final KafkaProducerService kafkaProducerService;

    public ClientController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PermitAll
    @RequestMapping(method = RequestMethod.GET, value = "/clients")
    public ResponseEntity<List<ClientModel>> getClients() {
        List<ClientModel> clients=clientUseCase.getClients();
        // Ajouter HATEOAS sur chaque client
        List<ClientModel> clientsWithLinks = clients.stream().map(client -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClientController.class)
                    .getClientById(client.getIdClient())).withSelfRel();
            client.add(selfLink);
            return client;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(clientsWithLinks,HttpStatus.OK);
    }


    @PermitAll
    @RequestMapping(method = RequestMethod.POST, value = "/clients/addClient")
    public ResponseEntity<String> addClient(@Valid @RequestBody ClientModel client) {
        client.setTypeOp("ADD");
        kafkaProducerService.sendClientEvent(client);
        return ResponseEntity.ok("Client ajoutée et événement envoyé");
    }


    @PermitAll
    @RequestMapping(method = RequestMethod.DELETE, value = "/clients/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        ClientModel clientModel = new ClientModel();
        clientModel.setIdClient(id);
        clientModel.setTypeOp("DELETE");
        kafkaProducerService.sendClientEvent(clientModel);
        return ResponseEntity.ok("Client supprimée et événement envoyé");
    }


    @PermitAll
    @RequestMapping(method = RequestMethod.GET, value = "/clients/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable Long id) {
        ClientModel client=clientUseCase.getClientById(id);
        if (client==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
    }


}
