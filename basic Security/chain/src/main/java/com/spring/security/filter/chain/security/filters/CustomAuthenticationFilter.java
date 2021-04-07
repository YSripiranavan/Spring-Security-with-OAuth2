package com.spring.security.filter.chain.security.filters;

import com.spring.security.filter.chain.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager manager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        var a = new CustomAuthentication(authorization,null);
        try {
            Authentication result = manager.authenticate(a);

            if (result.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(result);
                chain.doFilter(request, response);
            }else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (AuthenticationException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
