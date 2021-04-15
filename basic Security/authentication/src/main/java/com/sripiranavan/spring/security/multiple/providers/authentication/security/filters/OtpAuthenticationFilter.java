package com.sripiranavan.spring.security.multiple.providers.authentication.security.filters;

import com.sripiranavan.spring.security.multiple.providers.authentication.security.authentication.TokenAuthentication;
import com.sripiranavan.spring.security.multiple.providers.authentication.security.managers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class OtpAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        var token = request.getHeader("Authorization");

        Authentication authentication = new TokenAuthentication(token,null);
        var a = authenticationManager.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(a);
        chain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}
