package com.client.gestionClient.infrastructure.config;

import com.client.gestionClient.infrastructure.adapter.persistence.adapters.ClientAdapter;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public ClientAdapter clientAdapter() {
        return Mappers.getMapper(ClientAdapter.class);
    }
}
