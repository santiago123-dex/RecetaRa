package com.example.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
//Lo que hace es implementar esta interfaz para acceder a metodos especiales
class WebConfig implements WebMvcConfigurer{


    private final JwtInterceptor jwtInterceptor;

    public WebConfig(JwtInterceptor jwtInterceptor){
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //Registro de instacia del interceptor
        registry.addInterceptor(jwtInterceptor)
            //Definicion de rutas del interceptor estas rutas son la rutas protegidas
            .addPathPatterns("/api/**")
            //exclusion de rutas publicas
            .excludePathPatterns("/auth/**");
    }

    
}