package com.vag.product.config;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Clock;

// DIFF: Usually for this type of class we use @Configuration on Spring
@ApplicationScoped
public class ProductApplicationConfig {

    @ApplicationScoped
    Clock clock() {
        return Clock.systemDefaultZone();
    }

}
