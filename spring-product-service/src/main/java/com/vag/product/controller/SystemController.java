package com.vag.product.controller;

import com.vag.product.domain.SystemService;
import com.vag.product.properties.ProductServiceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(value = "/api/system")
@RequiredArgsConstructor
public class SystemController {

    private final SystemService systemService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductServiceProperties getApplicationProperties() {
        return systemService.getProductServiceProperties();
    }

    @GetMapping("/timestamp")
    public ZonedDateTime getTimestamp() {
        return systemService.getTimestamp();
    }

    @GetMapping("/version")
    public String getCurrentVersion() {
        return systemService.getCurrentVersion();
    }
}
