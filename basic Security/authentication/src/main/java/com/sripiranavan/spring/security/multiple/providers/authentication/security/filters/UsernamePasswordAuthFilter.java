package com.sripiranavan.spring.security.multiple.providers.authentication.security.filters;

import com.sripiranavan.spring.security.multiple.providers.authentication.entities.Otp;
import com.sripiranavan.spring.security.multiple.providers.authentication.repository.OtpRepository;
import com.sripiranavan.spring.security.multiple.providers.authentication.security.authentication.OtpAuthentication;
import com.sripiranavan.spring.security.multiple.providers.authentication.security.authentication.UsernamePasswordAuthentication;
import com.sripiranavan.spring.security.multiple.providers.authentication.security.managers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        Step 1: username & password
//        Step 2: username & otp
        var username = request.getHeader("username");
        var password = request.getHeader("password");
        var otp = request.getHeader("otp");
        if (otp == null){
//            Step 1
            Authentication a = new UsernamePasswordAuthentication(username,password);
            a = authenticationManager.authenticate(a);
//            we generate an OTP
            String code = String.valueOf(new Random().nextInt(9999)+10000);
            Otp otpEntity = new Otp();
            otpEntity.setUsername(username);
            otpEntity.setOtp(code);
            otpRepository.save(otpEntity);
        }else {
//            Step 2
            Authentication a = new OtpAuthentication(username,otp);
            a = authenticationManager.authenticate(a);
//            We issue a token
            var token = UUID.randomUUID().toString();
            tokenManager.add(token);
            response.setHeader("Authorization", token);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
