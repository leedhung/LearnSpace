package com.example.learn_space.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // tất cả endpoint
                .allowedOrigins("http://localhost:3000") // nguồn React
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}