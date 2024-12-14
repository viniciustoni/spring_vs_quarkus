package com.vag.product.dto;

import lombok.Data;

@Data
public class ProductSearchDto {
    // DIFF: We dont need to use any annotation on spring
    private String name;
    private String brand;
}
