package com.vag.product.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

// DIFF: TODO: add the respective one for spring
@RegisterForReflection
public record ProductDto(Long id, String name, String brand, BigDecimal unitPrice) {
}
