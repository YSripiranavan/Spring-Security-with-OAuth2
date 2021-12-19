package com.sripiranavan.spring.security.configure.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();
//        var u1 = User.withUsername("piranavan").password("12345").authorities("ROLE_manager").build();
//        var u2 = User.withUsername("sri").password("12345").authorities("ROLE_admin").build();
//        var u1 = User.withUsername("piranavan").password("12345").roles("ADMIN").build();
//        var u2 = User.withUsername("sri").password("12345").authorities("ROLE_MANAGER").build();
        var u1 = User.withUsername("sri").password("12345").authorities("read").build();
        var u2 = User.withUsername("piranavan").password("12345").authorities("write").build();
        uds.createUser(u1);
        uds.createUser(u2);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();

//        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().anyRequest().hasAnyAuthority("admin");
//        http.authorizeRequests().anyRequest().hasAnyAuthority("admin","manager");
//        http.authorizeRequests().anyRequest().hasRole("manager");
//        http.authorizeRequests().anyRequest().hasRole("ADMIN");
//        http.authorizeRequests().mvcMatchers("/hello").hasRole("ADMIN").mvcMatchers("/ciao").hasRole("MANAGER").anyRequest().permitAll();
//        http.authorizeRequests()
//                .mvcMatchers("/a/**").hasAuthority("read")
//                .mvcMatchers("/b/*").hasAuthority("write")
//                .mvcMatchers("/c/*").hasAuthority("read") // for one value after c
//                .mvcMatchers("/c/{name}").hasAuthority("read")
//                .anyRequest().denyAll();

        http.authorizeRequests()
//                .antMatchers("/a").authenticated() // only /a
//                .anyRequest().permitAll(); // this can access /a/
                .mvcMatchers(HttpMethod.GET,"/a").authenticated()
                .anyRequest().permitAll();
    }
}
