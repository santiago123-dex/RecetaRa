package com.example.backend.config;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.backend.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//Utilizamos este contrato para decir que va a ser un interceptor
public class JwtInterceptor implements HandlerInterceptor{

    private final JwtService jwtService;

    public JwtInterceptor(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{


        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            response.setStatus(401);
            response.getWriter().write("Aceeso denegado: No has enviado el token");
            return false;
        }

        //Lo que hace es que borra el bearer y solo nos quedamos con el codigo
        String token = authHeader.substring(7);

        //Trae la funcion del servicio y valida si el token es valido
        if(!jwtService.isTokenValid(token)){
            response.setStatus(401);
            response.getWriter().write("token invalido o expirado");
            return false;
        }


        return true;



    }
    
}
