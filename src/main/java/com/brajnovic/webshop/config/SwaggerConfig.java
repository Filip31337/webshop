package com.brajnovic.webshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {

        HttpAuthenticationScheme authenticationScheme = HttpAuthenticationScheme
                .JWT_BEARER_BUILDER
                .name("JWT Token")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.brajnovic.webshop.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(createApiInfo());
    }

    private ApiInfo createApiInfo() {
        final String title = "Webshop";
        final String description = "Simple webshop";
        final String version = "1.0";
        final String termsOfServiceUrl = "urn:tos";
        final Contact contact = new Contact("filip brajnovic", "www.google.com", "a@a.com");
        final String licence = "Apache 2.0";
        final String licenceUrl = "https://www.apache.org/licenses/LICENSE-2.0";
        return new ApiInfo(title, description, version, termsOfServiceUrl, contact, licence,
                licenceUrl, Collections.emptyList());
    }
}
