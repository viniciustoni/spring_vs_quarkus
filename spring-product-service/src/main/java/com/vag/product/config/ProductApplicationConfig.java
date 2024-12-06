package com.vag.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

// DIFF: On Quarkus, since it's using Jakarta annotations, it doesn't have any distinction on business layer like spring(Configuration, Service, repository, component).
// Also, remember that @Configuration, @Service, @Repository are meta-annotated with @Component.
// In the end of the day, we can only use @Component as Quarkus is using only @ApplicationScope, however, thanks for this "sub-types" on spring
// we can have our annotation more close to the Domain-Driven Design approach.
@Configuration
public class ProductApplicationConfig {

    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }

}
