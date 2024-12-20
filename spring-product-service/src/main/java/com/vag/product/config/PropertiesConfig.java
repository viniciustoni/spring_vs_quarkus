package com.vag.product.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

// DIFF: On Spring we will need to use the @ConfigurationPropertiesScan,
// on Quarkus we don't need to configure anything which makes it more simple than spring.
@Configuration
@ConfigurationPropertiesScan("com.vag.product.properties")
public class PropertiesConfig {
}
