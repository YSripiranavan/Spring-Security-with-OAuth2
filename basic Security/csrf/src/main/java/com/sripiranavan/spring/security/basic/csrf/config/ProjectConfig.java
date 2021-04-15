package com.sripiranavan.spring.security.basic.csrf.config;

import com.sripiranavan.spring.security.basic.csrf.security.CsrfTokenLoggerFilter;
import com.sripiranavan.spring.security.basic.csrf.security.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
//        http.csrf().disable();
        http.csrf(
                c -> {
                    c.ignoringAntMatchers("/disableCsrf/**");
//                    c.csrfTokenRepository(new CustomCsrfTokenRepository());
                }
        );
        http.addFilterAfter(new CsrfTokenLoggerFilter(), CsrfFilter.class);
    }
}
