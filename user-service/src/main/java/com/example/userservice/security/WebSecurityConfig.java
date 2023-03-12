package com.example.userservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String ADMIN = "avocat";
    public static final String USER = "user";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/avocat").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/client").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/agent").permitAll()
//                .requestMatchers(HttpMethod.POST, "/api/avocat", "/test/admin/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/api/avocat", "/test/admin/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.PUT, "/api/avocat", "/test/admin/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/avocat-list").hasAnyRole(ADMIN, USER)
                .anyRequest().authenticated();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

}
