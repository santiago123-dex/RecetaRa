package com.example.backend.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService {
    
    //Va a usar el valor del properties
    @Value("${jwt.secret}")
    private String secretKeyString;

    //se obtiene del archivo properties
    @Value("${jwt.expiration}")
    private Long expiration;

    // Se va a usar para firmar y verificar tokens
    private Key secretKey;

    // se pone esta notacion para decir que se va a ejecutar automaticamente 
    @PostConstruct
    private void init(){
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email){
        return Jwts.builder()
            //Se identifica por email
            .setSubject(email)
            // fecha de emision
            .setIssuedAt(new Date())
            // fecha en la que expira
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            //firma el token usando el algoritmo HS256
            .signWith(secretKey, SignatureAlgorithm.HS256)
            // Genera el jwt final
            .compact();
    }

    private Claims getClaims(String token){
        // se usa el parser para construir y verificar el token y demas de eso los puede firmar
        return Jwts.parserBuilder()
        // Se pasa la clave secreta que ya esta firmada
            .setSigningKey(secretKey)
            //Construye el parser asi que ya esta listo para analizar el token
            .build()
            //Aca decodifica y valida la firma quiere decir que verifica el token
            .parseClaimsJws(token)
            //Y se usa el body para obtener todo el cuerpo como el email
            .getBody();
    }

    // Decodifica el token usando el claims y obtiene el campo subject, normalmente 
    // se obtiene el email
    public String extractEmail(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token){
        try{
            Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date());

        }catch(JwtException | IllegalArgumentException e){
            return false;
        }
    }
    

}
