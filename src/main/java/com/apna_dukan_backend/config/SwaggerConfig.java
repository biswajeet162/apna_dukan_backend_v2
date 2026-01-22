package com.apna_dukan_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apnaDukanOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Apna Dukan Backend API")
                        .description("E-commerce Backend API for Apna Dukan Android App")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Apna Dukan Team")
                                .email("support@apnadukan.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT Authorization header using the Bearer scheme. Example: \"Authorization: Bearer {token}\"")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("com.apna_dukan_backend")
                .build();
    }
}

