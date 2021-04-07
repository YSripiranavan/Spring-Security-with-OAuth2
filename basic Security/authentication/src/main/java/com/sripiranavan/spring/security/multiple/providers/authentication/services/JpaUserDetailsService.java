package com.sripiranavan.spring.security.multiple.providers.authentication.services;

import com.sripiranavan.spring.security.multiple.providers.authentication.entities.User;
import com.sripiranavan.spring.security.multiple.providers.authentication.repository.UserRepository;
import com.sripiranavan.spring.security.multiple.providers.authentication.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var o = userRepository.findUserByUsername(username);
        User u = o.orElseThrow(() -> new UsernameNotFoundException("User name not found"));
        return new SecurityUser(u);
    }
}
