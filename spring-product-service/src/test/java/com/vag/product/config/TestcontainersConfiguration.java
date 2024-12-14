package com.vag.product.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

// DIFF: TestContainers on Quarkus are started automatically without any
// configuration in case we dont have the jdbc.url configured. It's smart enough to know which DB we want to.
// On Spring it requires some configuration
@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:17.2-alpine"))
                .withReuse(true);
    }

}
