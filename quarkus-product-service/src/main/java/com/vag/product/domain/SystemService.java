package com.vag.product.domain;

import com.vag.product.properties.ApplicationProperties;
import jakarta.enterprise.context.Dependent;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Clock;
import java.time.ZonedDateTime;

// DIFF: @Dependent it'll be similar to @Prototype on spring.
@Dependent
@RequiredArgsConstructor
public class SystemService {

    private final Clock clock;
    private final ApplicationProperties applicationProperties;

    @ConfigProperty(name = "application.build.version")
    String currentVersion;

    public ZonedDateTime getTimestamp() {
        return ZonedDateTime.now(clock);
    }

    public ApplicationProperties getApplicationProperties() {
        return applicationProperties;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }
}
