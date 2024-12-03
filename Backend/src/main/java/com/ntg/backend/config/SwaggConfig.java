package com.ntg.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggConfig {
    @Bean
    public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .info(new Info().title("Kesaa").description("All The Rest API for kesaa Project").version("1.0 SNAPSHOT"));
    }
}
