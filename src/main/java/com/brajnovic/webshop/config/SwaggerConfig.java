package com.brajnovic.webshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
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
