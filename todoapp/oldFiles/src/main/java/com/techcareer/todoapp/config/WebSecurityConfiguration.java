package com.techcareer.todoapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebFluxSecurity
public class WebSecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-ui.html/**",
            "/webjars/**"
    };

    //...
}
