package com.example.backend.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public UserEntity registrarUsuario(UserEntity user){
        // se comprueba si el email existe
        if (userRepository.findByEmail(user.getEmail())!= null)  {
            throw new RuntimeException("Email ya existe");
        }

        // se encripta la contraseña y se guarda en la base de datos
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setUsername(user.getUsername());
        return userRepository.save(user);
    }

    // buscar por email para el logueo
   

    //perfil del usuario
    public UserEntity getUser(Long id){
        //Se obtiene el id
        return userRepository.findById(id)
        // lanza error al no encontrar nada
            .orElseThrow(() -> new RuntimeException("usuario no encontrado"));
    }



}
