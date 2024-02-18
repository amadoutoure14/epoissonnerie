package com.source.epoissonnerie.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("E-Poissonnerie")
                                .description("API pour l'application E-Poissonnerie")
                                .version("1.0")
                );
    }
}