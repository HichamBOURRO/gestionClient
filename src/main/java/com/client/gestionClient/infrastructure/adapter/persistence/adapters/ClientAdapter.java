package com.client.gestionClient.infrastructure.adapter.persistence.adapters;

import com.client.gestionClient.domain.model.ClientModel;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientAdapter {
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "prenom", target = "prenom")
    @Mapping(source = "idClient", target = "idClient")
    @Mapping(source = "motDePasse", target = "motDePasse")
    @Mapping(source = "email", target = "email")
    ClientEntity toEntity(ClientModel client);

    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "prenom", target = "prenom")
    @Mapping(source = "idClient", target = "idClient")
    @Mapping(source = "motDePasse", target = "motDePasse")
    @Mapping(source = "email", target = "email")
    ClientModel toModel(ClientEntity entity);

    // 🔹 Ajout du mapping pour les Listes
    List<ClientEntity> toEntityList(List<ClientModel> clients);
    List<ClientModel> toModelList(List<ClientEntity> entities);
}
