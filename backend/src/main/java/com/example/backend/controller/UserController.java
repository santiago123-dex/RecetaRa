package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.AuthResponse;
import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.LoginResponse;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.dto.RegisterResponse;
import com.example.backend.entity.UserEntity;
import com.example.backend.service.UserService;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registrarUsuario(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.registrarUsuario(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping("/mostrarUsuarios")
    public ResponseEntity<List<AuthResponse>> getMethodName() {
        return ResponseEntity.ok(userService.listarUsuarios());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<UserEntity>> buscarId (@PathVariable Long id) {
        return ResponseEntity.ok(userService.buscarId(id));
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable Long id) {
        userService.borrarUsuario(id);
        return ResponseEntity.ok("Usuario borrado correctamente");
    }
    


    
    

}
