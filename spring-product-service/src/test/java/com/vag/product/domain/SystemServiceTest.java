package com.vag.product.domain;

import com.vag.product.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.*;

import static com.vag.product.domain.SystemServiceTest.ClockConfiguration.FIXED_ZONED_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
class SystemServiceTest {

    @Autowired
    private SystemService systemService;

    @Test
    void shouldGetMockedTimeStamp() {
        // when
        var currentTimeStamp = systemService.getTimestamp();

        // then
        assertThat(currentTimeStamp).isEqualTo(FIXED_ZONED_DATE_TIME);
    }

    @TestConfiguration
    public static class ClockConfiguration {
        public static final LocalDate FIXED_LOCAL_DATE = LocalDate.of(2024, Month.DECEMBER, 4);
        public static final LocalTime FIXED_LOCAL_TIME = LocalTime.of(13, 30);
        public static final ZonedDateTime FIXED_ZONED_DATE_TIME = ZonedDateTime.of(FIXED_LOCAL_DATE, FIXED_LOCAL_TIME, ZoneId.systemDefault());

        @Bean
        @Primary
        Clock clock() {
            return Clock.fixed(FIXED_ZONED_DATE_TIME.toInstant(), ZoneId.systemDefault());
        }
    }
}