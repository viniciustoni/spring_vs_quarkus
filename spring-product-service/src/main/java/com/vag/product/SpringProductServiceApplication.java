package com.vag.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// DIFF: On Quarkus we don't need such class, however we can create if we want to.
@SpringBootApplication
public class SpringProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringProductServiceApplication.class, args);
    }

}
