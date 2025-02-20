package com.client.gestionClient.infrastructure.adapter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "t_livraison")
public class LivraisonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livraison")

    private long idLivraison;

    @Transient
    private String type;

    @Transient
    private String email;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_Livraison")
    private LocalDateTime dateLivraison;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client_f")
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_type_livraison_f")
    private TypeLivraisonEntity typeLivraison;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(long idLivraison) {
        this.idLivraison = idLivraison;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDateTime dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public TypeLivraisonEntity getTypeLivraison() {
        return typeLivraison;
    }

    public void setTypeLivraison(TypeLivraisonEntity typeLivraison) {
        this.typeLivraison = typeLivraison;
    }


}
