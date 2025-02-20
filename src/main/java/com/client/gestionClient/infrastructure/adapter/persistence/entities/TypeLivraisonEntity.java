package com.client.gestionClient.infrastructure.adapter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "t_type_livraison")
public class TypeLivraisonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_livraison")
    private long idTypeLivraison;

    @Column(name = "type_livraison")
    private String typeLivraison;

    public String getTypeLivraison() {
        return typeLivraison;
    }

    public void setTypeLivraison(String typeLivraison) {
        this.typeLivraison = typeLivraison;
    }

    public long getIdTypeLivraison() {
        return idTypeLivraison;
    }

    public void setIdTypeLivraison(long idTypeLivraison) {
        this.idTypeLivraison = idTypeLivraison;
    }
}
