package com.vag.product;

import com.vag.product.config.TestcontainersConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

// DIFF: I needed at least 3 annotation to start our test with testContainer and with integration test profile,
// on Quarkus a single @QuarkusTest will start the testcontainer, and use the test profile
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
@Import({TestcontainersConfiguration.class})
@ActiveProfiles("integration-test")

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IntegrationTest {
}
