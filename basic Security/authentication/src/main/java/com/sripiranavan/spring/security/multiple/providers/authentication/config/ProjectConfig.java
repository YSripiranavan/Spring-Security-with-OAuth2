package com.sripiranavan.spring.security.multiple.providers.authentication.config;

import com.sripiranavan.spring.security.multiple.providers.authentication.security.filters.OtpAuthenticationFilter;
import com.sripiranavan.spring.security.multiple.providers.authentication.security.filters.UsernamePasswordAuthFilter;
import com.sripiranavan.spring.security.multiple.providers.authentication.security.providers.OtpAuthenticationProvider;
import com.sripiranavan.spring.security.multiple.providers.authentication.security.providers.TokenAuthProvider;
import com.sripiranavan.spring.security.multiple.providers.authentication.security.providers.UsernamePasswordAuthProviders;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
//@EnableAsync
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernamePasswordAuthProviders authProviders;
    @Autowired
    private OtpAuthenticationProvider otpAuthenticationProvider;
    @Autowired
    private TokenAuthProvider tokenAuthProvider;

    @Bean
    public UsernamePasswordAuthFilter usernamePasswordAuthFilter(){
        return new UsernamePasswordAuthFilter();
    }

    @Bean
    public OtpAuthenticationFilter otpAuthenticationFilter(){
        return new OtpAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProviders).authenticationProvider(otpAuthenticationProvider)
        .authenticationProvider(tokenAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(usernamePasswordAuthFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(otpAuthenticationFilter(),BasicAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public InitializingBean initializingBean(){
//        return () -> {
//            SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//        };
//    }
}
