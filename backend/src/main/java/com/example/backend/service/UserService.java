package com.example.backend.service;


import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.LoginResponse;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.dto.RegisterResponse;
import com.example.backend.entity.UserEntity;
import com.example.backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    //Hashea las contraseñas y se puede verificar contraseñas
    private final BCryptPasswordEncoder passwordEncoder;


    //inyeccion por el constructor, cuando se instancie el servicio pasa las dependencias
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Registrar usuario
    public RegisterResponse registrarUsuario(RegisterRequest request){
        // se comprueba si el email existe
        if (userRepository.findByEmail(request.getEmail()).isPresent())  {
            throw new RuntimeException("Email ya existe");
        }

        UserEntity user = UserEntity.builder()
            .nombreUsuario(request.getNombreUsuario())
            .email(request.getEmail())
            .contrasena(passwordEncoder.encode(request.getContrasena()))
            .build();

        userRepository.save(user);
        return new RegisterResponse(user.getId(), user.getEmail(), user.getNombreUsuario() ,"Usuario registrado correctamente");
    }

    // buscar por email para el logueo
    public LoginResponse login(LoginRequest request){

        Optional<UserEntity> userOpt = userRepository.findByEmail(request.getEmail());

        if (!userOpt.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        UserEntity user = userOpt.get();

        if (!passwordEncoder.matches(request.getContrasena(), user.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new LoginResponse(null, "Inicio de sesion exitoso");
    }




}
