package com.vag.product.properties;

import io.smallrye.config.ConfigMapping;

import java.time.ZonedDateTime;

// TODO: serialize
@ConfigMapping(namingStrategy = ConfigMapping.NamingStrategy.VERBATIM, prefix = "application")
public interface ApplicationProperties {

    String name();

    Build build();

    interface Build {
        String version();

        ZonedDateTime timestamp();
    }
}
