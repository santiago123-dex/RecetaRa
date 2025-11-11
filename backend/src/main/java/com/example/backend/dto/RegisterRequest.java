package com.example.backend.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nombreUsuario;
    private String email;
    private String contrasena;
}
