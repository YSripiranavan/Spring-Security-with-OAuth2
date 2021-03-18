package com.sripiranavan.spring.security.nonopaque.nonopaqueauthorization.repository;

import com.sripiranavan.spring.security.nonopaque.nonopaqueauthorization.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findClientByClientId(String integer);
}
