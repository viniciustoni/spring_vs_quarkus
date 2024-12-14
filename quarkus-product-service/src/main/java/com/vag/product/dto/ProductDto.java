package com.vag.product.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;

// DIFF: @RegisterForReflection means that we are marking this class to be used with reflection on native image.
// On spring we should use @RegisterReflectionForBinding(ProductDto.class), however we will need to provide all the classes that
// we want to register for reflection.
@Builder(toBuilder = true)
@RegisterForReflection
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
