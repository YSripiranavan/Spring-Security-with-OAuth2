package com.sripiranavan.spring.security.nonopaque.nonopaqueauthorization.security;

import com.sripiranavan.spring.security.nonopaque.nonopaqueauthorization.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

public class JpaClientDetailsService implements ClientDetailsService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String s) {
        return clientRepository.findClientByClientId(s).map(
                c -> new SecurityClient(c)
        ).orElseThrow(() -> new ClientRegistrationException(":("));
    }
}
