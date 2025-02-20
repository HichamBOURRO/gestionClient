package com.client.gestionClient.infrastructure.config.security;

import com.client.gestionClient.infrastructure.adapter.persistence.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.role}")
    private String adminRole;

    public CustomUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Vérifier si l'utilisateur est l'admin défini en properties
        if (username.equals(adminUsername)) {
            return new User(adminUsername, adminPassword, List.of(new SimpleGrantedAuthority("ROLE_" + adminRole)));
        }

        // Sinon, chercher l'utilisateur en base
        return clientRepository.findByEmail(username)
                .map(client -> new User(client.getEmail(), client.getMotDePasse(),
                        List.of(new SimpleGrantedAuthority("ROLE_" + client.getRole().name()))))
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
    }
}

