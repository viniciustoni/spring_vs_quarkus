package com.vag.product.config;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Clock;

@ApplicationScoped
public class ProductApplicationConfig {

    @ApplicationScoped
    Clock clock() {
        return Clock.systemDefaultZone();
    }

}
