package com.codiecode.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedOpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${spring.application.name:Service API}") String appName,
            @Value("${app.version:1.0.0}") String appVersion) {

        return new OpenAPI()
                .info(new Info()
                        .title(appName.toUpperCase()) // e.g., RESTAURANT-SERVICE
                        .description("Automated API Documentation for " + appName)
                        .version(appVersion));
    }
}