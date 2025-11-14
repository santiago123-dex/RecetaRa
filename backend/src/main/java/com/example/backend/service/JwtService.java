package com.example.backend.service;

import org.springframework.beans.factory.annotation.Value;


public class JwtService {
    
    //Va a usar el valor del properties
    @Value("${jwt.secret}")
    private String secretKey;

    //se obtiene del archivo properties
    @Value("${jwt.expiration}")
    private Long expiration;

    

}
