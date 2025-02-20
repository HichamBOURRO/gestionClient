package com.client.gestionClient.application.controllers;

import com.client.gestionClient.application.service.KafkaProducerService;
import com.client.gestionClient.domain.model.LivraisonModel;
import com.client.gestionClient.domain.port.input.ClientUseCase;
import com.client.gestionClient.domain.port.input.LivraisonUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LivraisonController {

    @Autowired(required=true)
    LivraisonUseCase livraisonUseCase;

    @Autowired(required=true)
    ClientUseCase clientUseCase;

    private final KafkaProducerService kafkaProducerService;

    public LivraisonController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }


    /**
     * Ajoute une nouvelle livraison et envoie un événement Kafka.
     *
     * @param livraison Livraison à ajouter
     * @return Message de confirmation
     */
    @Operation(summary = "Ajouter une nouvelle livraison")
    @PreAuthorize("permitAll()")
    @PostMapping("/livraisons/addLivraison")
    public ResponseEntity<String> addLivraison( @RequestBody LivraisonModel livraison) {
        livraison.setTypeOp("ADD");
        kafkaProducerService.sendLivraisonEvent(livraison);
        return ResponseEntity.ok("Livraison ajoutée et événement envoyé");
    }


    /**
     * Suppression de Livrasion par ID.
     *
     * @param id à supprimer
     * @return Message de confirmation et envoie un événement Kafka.
     */
    @Operation(summary = "Suppression d'une livraison par ID")
    @PermitAll
    @RequestMapping(method = RequestMethod.DELETE, value = "/livraisons/{id}")
    public ResponseEntity<String> deleteLivraison(@PathVariable Long id) {
        LivraisonModel livraisonModel = new LivraisonModel();
        livraisonModel.setIdLivraison(id);
        livraisonModel.setTypeOp("DELETE");
        kafkaProducerService.sendLivraisonEvent(livraisonModel);
        return ResponseEntity.ok("Livraison supprimée et événement envoyé");
    }


    /**
     * Recherche de Livrasion par ID.
     *
     * @param id à chercher
     * @return Message de confirmation et envoie un événement Kafka.
     */
    @Operation(summary = "Recherche d'une livraison par ID")
    @PermitAll
    @RequestMapping(method = RequestMethod.GET, value = "/livraisons/{id}")
    public ResponseEntity<LivraisonModel> getLivraisonById(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // Peut être l'email ou le nom d'utilisateur
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        LivraisonModel livraison=null;
        if (isAdmin) {
            livraison=livraisonUseCase.getLivraisonById(id);
        }else{
            livraison=livraisonUseCase.getLivraisonByIdAndEmail(id,username);
        }
        if (livraison==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(livraison, HttpStatus.OK);
        }
    }


    /**
     * Recuperer toutes les livraisons sous condition que l'utilisateur est ADMIN sinon recuperer uniquement les livraison du client qui s'est authentifié.
     *
     * @return liste des livraisons.
     */
    @Operation(summary = "Recuperer toutes les livraisons")

    @PermitAll
    @RequestMapping(method = RequestMethod.GET, value = "/livraisons")
    public ResponseEntity<List<LivraisonModel>> getLivraisons() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // Peut être l'email ou le nom d'utilisateur
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        List<LivraisonModel> livraisons=null;

        if (isAdmin) {
            livraisons=livraisonUseCase.getLivraisons();
        }else{
            livraisons=livraisonUseCase.getLivraisonsByEmail(username);
        }

        // Ajouter HATEOAS sur chaque client
        List<LivraisonModel> livraisonsWithLinks = livraisons.stream().map(livraison -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LivraisonController.class)
                    .getLivraisonById(livraison.getIdLivraison())).withSelfRel();
            livraison.add(selfLink);
            return livraison;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(livraisonsWithLinks, HttpStatus.OK);
    }
}
