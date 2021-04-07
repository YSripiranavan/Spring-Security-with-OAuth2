package com.sripiranavan.spring.security.multiple.providers.authentication.repository;

import com.sripiranavan.spring.security.multiple.providers.authentication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByUsername(String username);
}
