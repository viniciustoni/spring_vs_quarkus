package com.vag.product.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;

// DIFF: TODO: add the respective one for spring
@Builder(toBuilder = true)
public record ProductDto(
        @Null(groups = InsertProductGroup.class, message = "Id must be null.")
        Long id,

        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Size(max = 100)
        String brand,

        @Digits(integer = 10, fraction = 2)
        BigDecimal unitPrice) {


    public interface InsertProductGroup {
    }
}
