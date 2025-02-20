package com.client.gestionClient.infrastructure.config.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity // Active @PreAuthorize et @Secured
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactive CSRF pour permettre POST sans token
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // Autorise cet endpoint
                        .anyRequest().authenticated() // Auth obligatoire pour le reste
                )
                .httpBasic(withDefaults()) // Authentification HTTP basique
                .logout(logout -> logout.logoutSuccessUrl("/")); // Déconnexion

        return http.build();
    }
}
