package com.client.gestionClient.infrastructure.adapter.persistence.adapters;

import com.client.gestionClient.domain.model.LivraisonModel;
import com.client.gestionClient.infrastructure.adapter.persistence.entities.LivraisonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LivraisonAdapter {
    @Mapping(source = "idLivraison", target = "idLivraison")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "dateLivraison", target = "dateLivraison", qualifiedByName = "offsetDateTimeToLocalDateTime")
    @Mapping(source = "dateCreation", target = "dateCreation", qualifiedByName = "offsetDateTimeToLocalDateTime")
    LivraisonEntity toEntity(LivraisonModel livraision);

    @Mapping(source = "idLivraison", target = "idLivraison")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "dateLivraison", target = "dateLivraison", qualifiedByName = "localDateTimeToOffsetDateTime")
    @Mapping(source = "dateCreation", target = "dateCreation", qualifiedByName = "localDateTimeToOffsetDateTime")

    LivraisonModel toModel(LivraisonEntity entity);

    // 🔹 Ajout du mapping pour les Listes
    List<LivraisonEntity> toEntityList(List<LivraisonModel> livaisons);
    List<LivraisonModel> toModelList(List<LivraisonEntity> entities);

    @Named("offsetDateTimeToLocalDateTime")
    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }

    @Named("localDateTimeToOffsetDateTime")
    default OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }


}
