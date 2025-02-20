package com.client.gestionClient.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.hateoas.RepresentationModel;



@Schema(description = "Représentation d'un client")
public class ClientModel  extends RepresentationModel<ClientModel> {
    @Schema(description = "Identifiant unique du client", example = "1")
    private Long idClient;

    @Schema(description = "Nom du client", example = "Dupont")
    private String nom;

    @Schema(description = "Prénom du client", example = "Jean")
    private String prenom;

    @Schema(description = "Mot de passe du client", example = "password123")
    private String motDePasse;

    @Schema(description = "Email du client", example = "jean.dupont@example.com")
    @Email(message = "L'email doit être valide")
    @NotBlank(message = "L'email ne peut pas être vide")
    private String email;

    @Schema(description = "Type d'Operation, ça peut prendre une des deux valeurs: [ADD: pour l'insertion, DELETE: Suppression]", example = "{ADD,DELETE}")
    private String typeOp;

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypeOp() {
        return typeOp;
    }

    public void setTypeOp(String typeOp) {
        this.typeOp = typeOp;
    }
}
