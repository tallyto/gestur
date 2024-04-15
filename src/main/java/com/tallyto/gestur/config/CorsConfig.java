package com.tallyto.gestur.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Permitir solicitações de qualquer origem
                .allowedMethods("*") // Métodos permitidos
                .allowedHeaders("*"); // Headers permitidos
    }
}