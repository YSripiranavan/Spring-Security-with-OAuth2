package com.sripiranavan.spring.security.multiple.providers.authentication.security.providers;

import com.sripiranavan.spring.security.multiple.providers.authentication.security.authentication.UsernamePasswordAuthentication;
import com.sripiranavan.spring.security.multiple.providers.authentication.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsernamePasswordAuthProviders implements AuthenticationProvider {
    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails user = userDetailsService.loadUserByUsername(username);
        String test = passwordEncoder.encode(password);
        if (passwordEncoder.matches(password,user.getPassword())){
            return new UsernamePasswordAuthentication(username,passwordEncoder.encode(password), List.of(() -> "read"));
        }
        throw new BadCredentialsException("Error");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthentication.class.equals(aClass);
    }
}
