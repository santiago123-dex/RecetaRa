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
        if (userRepository.findByEmail(request.getEmail())!= null)  {
            throw new RuntimeException("Email ya existe");
        }

        // se encripta la contraseña y se guarda en la base de datos
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPasswordHash()));

        UserEntity savedUser = userRepository.save(user);
        return new RegisterResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    // buscar por email para el logueo
    public LoginResponse login(LoginRequest request){
        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        UserEntity userEntity = user.get();

        if (!passwordEncoder.matches(request.getPasswordHash(), userEntity.getPasswordHash())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new LoginResponse(null, null);
    }


    //perfil del usuario
    public UserEntity getUser(Long id){
        //Se obtiene el id
        return userRepository.findById(id)
        // lanza error al no encontrar nada
            .orElseThrow(() -> new RuntimeException("usuario no encontrado"));
    }



}
