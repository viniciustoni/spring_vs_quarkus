package com.vag.product.domain;

import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

// DIFF: @QuarkusTest is similar to @SpringBootTest(webEnvironment = RANDOM_PORT)
@QuarkusTest
@RequiredArgsConstructor
class SystemServiceTest {

    private final SystemService systemService;

    @Test
    void shouldGetMockedTimeStamp() {
        // when
        var currentTimeStamp = systemService.getTimestamp();

        // then

        assertThat(currentTimeStamp).isEqualTo(TestConfiguration.FIXED_ZONED_DATE_TIME);
    }


    static class TestConfiguration {
        public static final LocalDate FIXED_LOCAL_DATE = LocalDate.of(2024, Month.DECEMBER, 4);
        public static final LocalTime FIXED_LOCAL_TIME = LocalTime.of(13, 30);
        public static final ZonedDateTime FIXED_ZONED_DATE_TIME = ZonedDateTime.of(FIXED_LOCAL_DATE, FIXED_LOCAL_TIME, ZoneId.systemDefault());

        // DIFF: If we want to override a bean on quarkus we can create our method with @Mock, we can also use @Mock on our class.
        // On Spring we will need to create a method with @Bean and @Primary OR @MockitoBean
        @Mock
        Clock clock() {
            return Clock.fixed(FIXED_ZONED_DATE_TIME.toInstant(), ZoneId.systemDefault());
        }

    }

}