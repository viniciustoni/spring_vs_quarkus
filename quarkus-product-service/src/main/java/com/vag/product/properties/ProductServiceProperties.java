package com.vag.product.properties;

import io.smallrye.config.ConfigMapping;

import java.time.ZonedDateTime;

// DIFF: On Spring we should use @ConfigurationProperties(prefix = "info.product-service"), and we can use Record in there,
// here we can go with interface - however when I'm trying to return on response it returns empty :D
@ConfigMapping(namingStrategy = ConfigMapping.NamingStrategy.VERBATIM, prefix = "info.product-service")
public interface ProductServiceProperties {

    Application application();

    Build build();

    interface Application {
        String name();
    }

    interface Build {
        String version();

        ZonedDateTime timestamp();
    }
}
