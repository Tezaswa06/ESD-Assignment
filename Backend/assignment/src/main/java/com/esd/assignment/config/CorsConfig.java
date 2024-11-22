package com.esd.assignment.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Allow frontend origin
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS") // Add OPTIONS
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
