package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.dto.AuthResponse;
import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.LoginResponse;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.dto.RegisterResponse;
import com.example.backend.entity.UserEntity;
import com.example.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    // Hashea las contraseñas y se puede verificar contraseñas
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // inyeccion por el constructor, cuando se instancie el servicio pasa las
    // dependencias
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    // Registrar usuario
    public RegisterResponse registrarUsuario(RegisterRequest request) {
        // se comprueba si el email existe
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya existe");
        }

        UserEntity user = UserEntity.builder()
                .nombreUsuario(request.getNombreUsuario())
                .email(request.getEmail())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .build();

        userRepository.save(user);
        return new RegisterResponse(user.getId(), user.getEmail(), user.getNombreUsuario(),
                "Usuario registrado correctamente");
    }

    @Transactional
    // buscar por email para el logueo
    public LoginResponse login(LoginRequest request) {

        Optional<UserEntity> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        UserEntity user = userOpt.get();

        if (!passwordEncoder.matches(request.getContrasena(), user.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token, "Inicio de sesion exitoso");
    }

    @Transactional
    public List<AuthResponse> listarUsuarios() {

        return userRepository.findAll().stream()
                .map(user -> AuthResponse.builder()
                        .id(user.getId().toString())
                        .email(user.getEmail())
                        .build())
                .toList();

    }

    @Transactional
    public AuthResponse buscarId(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        // El builder es usado como si fuera un constructor para crear objetos de este  
        AuthResponse response = AuthResponse.builder()
            .id(user.getId().toString())
            .nombreUsuario(user.getNombreUsuario())
            .email(user.getEmail())
            .build();

            return response;
    }

    @Transactional
    public void borrarUsuario(Long id) {
        userRepository.deleteById(id);
    }

}
