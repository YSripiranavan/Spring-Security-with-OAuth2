package com.sripiranavan.spring.security.multiple.providers.authentication.repository;

import com.sripiranavan.spring.security.multiple.providers.authentication.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp,Integer> {

    Optional<Otp> findOtpByUsername(String username);
}
