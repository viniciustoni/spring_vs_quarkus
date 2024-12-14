package com.vag.product.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

// DIFF: On Quarkus we should use @ConfigMapping(prefix = "info.product-service") and we must use interfacein there,
// here we can use record or class and we need to use @ConfigurationProperties
@ConfigurationProperties(prefix = "info.product-service")
public record ProductServiceProperties(Application application, Build build) {

    public record Application(String name) {
    }

    public record Build(String version, String timestamp) {
    }
}
