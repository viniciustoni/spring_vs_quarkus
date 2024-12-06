package com.vag.product.domain;

import com.vag.product.properties.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZonedDateTime;

// DIFF: On spring to create a prototype, similar to @Dependent on quarkus, we will need to use @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class SystemService {

    private final Clock clock;
    private final ApplicationProperties applicationProperties;

    @Value("${info.spring-product-service.build.version}")
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
