package com.vag.product.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

// DIFF: TODO: add the respective one for spring
@RegisterForReflection
public record ProductDto(
        @Null(groups = InsertProductGroup.class, message = "Id must be null.")
        @NotNull(groups = UpdateProductGroup.class, message = "Id must not be null.")
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

    public interface UpdateProductGroup {
    }

}
