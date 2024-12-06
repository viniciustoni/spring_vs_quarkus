package com.vag.product.domain;

import com.vag.product.IntegrationTest;
import com.vag.product.config.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        assertThat(currentTimeStamp).isEqualTo(TestcontainersConfiguration.FIXED_ZONED_DATE_TIME);
    }
}