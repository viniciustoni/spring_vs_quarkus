package com.vag.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// DIFF: On quarkus we can do it with properties, I saw once that we can do the same on Spring, but I didn't find it at this time.
@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi productApi() {
        return GroupedOpenApi.builder()
                .group("Product API")
                .pathsToMatch("/api/product/**")
                .build();
    }

    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("System API")
                .pathsToMatch("/api/system/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${info.spring-product-service.build.version}") String version) {

        var license = new License()
                .name("MIT License")
                .url("https://github.com/viniciustoni/spring_vs_quarkus/blob/main/LICENSE");
        var contact = new Contact()
                .name("Vinicius Antonio Gai")
                .email("viniciustoni@gmail.com")
                .url("https://github.com/viniciustoni/");

        return new OpenAPI()
                .info(new Info()
                        .title("Spring vs Quarkus PoC")
                        .description("Small project to compare spring with quarkus")
                        .version(version)
                        .license(license)
                        .contact(contact)
                );
    }

}
