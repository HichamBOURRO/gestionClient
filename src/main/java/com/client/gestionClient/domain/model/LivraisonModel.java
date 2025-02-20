package com.client.gestionClient.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.time.OffsetDateTime;

@Schema(description = "Représentation d'une Livraison")
public class LivraisonModel  extends RepresentationModel<LivraisonModel> {

    @Schema(description = "Identifiant unique d'une Livraison", example = "1")
    private Long idLivraison;

    @Schema(description = "Type de la livraison, une de ces valeurs [DRIVE, DELIVERY, DELIVERY_TODAY, DELIVERY_ASAP]", example = "DELIVERY_ASAP")
    private String type;

    @Schema(description = "Email Du client", example = "dupont.mickael@gmail.com")
    private String email;

    @Schema(description = "Date de livraison, La date doit respecter le format dans l'exemple", example = "2025-02-19T16:28:11.011Z")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateLivraison;

    @Schema(description = "Date de création, peut etre auto-ajouté au cas non-saisi", example = "2025-02-19T16:28:11.011Z")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateCreation;

    @Schema(description = "Type d'Operation, ça peut prendre une des deux valeurs: [ADD: pour l'insertion, DELETE: Suppression]", example = "{ADD,DELETE}")
    private String typeOp;

    public Long getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Long idLivraison) {
        this.idLivraison = idLivraison;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OffsetDateTime getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(OffsetDateTime dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public OffsetDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(OffsetDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getTypeOp() {
        return typeOp;
    }

    public void setTypeOp(String typeOp) {
        this.typeOp = typeOp;
    }
}
