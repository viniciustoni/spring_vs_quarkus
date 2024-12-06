package com.vag.product.config;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.*;

@Configuration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    public static final LocalDate FIXED_LOCAL_DATE = LocalDate.of(2024, Month.DECEMBER, 4);
    public static final LocalTime FIXED_LOCAL_TIME = LocalTime.of(13, 30);
    public static final ZonedDateTime FIXED_ZONED_DATE_TIME = ZonedDateTime.of(FIXED_LOCAL_DATE, FIXED_LOCAL_TIME, ZoneId.systemDefault());

    @Bean
    @Primary
    Clock clock() {
        return Clock.fixed(FIXED_ZONED_DATE_TIME.toInstant(), ZoneId.systemDefault());
    }

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
    }

}
