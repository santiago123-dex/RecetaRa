package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {
    
    @Bean
    //devuelve un objeto de tipo BCryptPasswordEncoder
    public BCryptPasswordEncoder passwordEncoder(){
        //Se crea la instacia que proporciona spring security y esta se usa para encriptar las contraseñas
        return new BCryptPasswordEncoder();
    }

}
