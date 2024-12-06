package com.vag.product;

import org.springframework.boot.SpringApplication;
import org.testcontainers.utility.TestcontainersConfiguration;

public class TestSpringProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpringProductServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
