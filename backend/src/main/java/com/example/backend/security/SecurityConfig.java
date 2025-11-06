package com.example.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
public class SecurityConfig {
    
    @Bean
    //devuelve un objeto de tipo BCryptPasswordEncoder
    public BCryptPasswordEncoder passwordEncoder(){
        //Se crea la instacia que proporciona spring security y esta se usa para encriptar las contraseñas
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configure(http))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> httpBasic.disable()) // Deshabilita HTTP Basic
            .formLogin(formLogin -> formLogin.disable()) // Deshabilita form login
            .sessionManagement(session -> session.sessionCreationPolicy(
                org.springframework.security.config.http.SessionCreationPolicy.STATELESS
            )); // API sin estado (stateless)
    
        return http.build();
    }
}
