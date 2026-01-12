package com.codiecode.userinfo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Info API")
                        .description("Spring Boot 3.5 API for managing users")
                        .version("v1.0.0"));
    }
}