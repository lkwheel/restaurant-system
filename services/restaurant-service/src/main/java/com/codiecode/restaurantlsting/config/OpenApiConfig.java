package com.codiecode.restaurantlsting.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI restaurantOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restaurant API")
                        .description("Spring Boot 3.5 API for managing restaurants")
                        .version("v1.0.1-SNAPSHOT"));
    }
}