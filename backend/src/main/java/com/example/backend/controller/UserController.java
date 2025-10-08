package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.UserEntity;
import com.example.backend.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/registrar")
    // reponseEntity para devolver el estado del metodo
    // el RequestBody sirve para obtener el json y mandarlo en objeto para crear el usuario
    public ResponseEntity<UserEntity> registrarUser(@RequestBody UserEntity user){
        try{
            UserEntity crearUsuario = userService.registrarUsuario(user);
            return ResponseEntity.ok(crearUsuario);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
