package com.vag.product.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

// TODO: serialize
@Data
@ConfigurationProperties(prefix = "info.spring-product-service")
public class ApplicationProperties {

    private Application application;

    private Build build;

    @Data
    public static class Application {
        private String name;
    }

    @Data
    public static class Build {
        private String version;
        private String timestamp;
    }
}
