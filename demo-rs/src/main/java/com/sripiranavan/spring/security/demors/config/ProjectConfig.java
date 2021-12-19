package com.sripiranavan.spring.security.demors.config;

import com.nimbusds.jose.shaded.json.JSONArray;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.stream.Collectors;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                c -> c.jwt(
                        j -> j.decoder(decoder())
                                .jwtAuthenticationConverter(converter())
                )
        );
//        http.authorizeRequests().anyRequest().authenticated();
//        http.authorizeRequests().mvcMatchers("/demo/**").hasAuthority("write");
        http.authorizeRequests().anyRequest().authenticated();
    }
    @Bean
    public JwtDecoder decoder() {
        String key = "fasdfasdfasflkjkljaoisdfjklasdflasdfwerasdf";
        SecretKey key1 = new SecretKeySpec(key.getBytes(),0,key.getBytes().length,"AES");
        return NimbusJwtDecoder.withSecretKey(key1).build();
    }

    @Bean
    public JwtAuthenticationConverter converter(){
        var conv = new JwtAuthenticationConverter();
        conv.setJwtGrantedAuthoritiesConverter(jwt -> {
            JSONArray a = (JSONArray) jwt.getClaims().get("authorities");
            return a.stream()
                    .map(String::valueOf)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });
        return conv;
    }
}
