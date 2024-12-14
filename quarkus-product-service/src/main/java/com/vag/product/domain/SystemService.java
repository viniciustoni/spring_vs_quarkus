package com.vag.product.domain;

import com.vag.product.properties.ProductServiceProperties;
import jakarta.enterprise.context.Dependent;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Clock;
import java.time.ZonedDateTime;

// DIFF: @Dependent it'll be similar to @Scope(Prototype) on spring.
@Dependent
@RequiredArgsConstructor
public class SystemService {

    private final Clock clock;
    private final ProductServiceProperties productServiceProperties;

    // DIFF: On Spring will be @Value. Notice that in here we don't need to use ${} and also default
    // value is a second property, imho more readable
    @ConfigProperty(name = "info.product-service.build.version", defaultValue = "-")
    private final String currentVersion;

    public ZonedDateTime getTimestamp() {
        return ZonedDateTime.now(clock);
    }

    public ProductServiceProperties getProductServiceProperties() {
        return productServiceProperties;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }
}
