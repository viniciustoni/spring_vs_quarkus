package com.vag.product.dto;

import jakarta.ws.rs.QueryParam;
import lombok.Data;

@Data
public class ProductSearchDto {
    // DIFF: We dont need to use any annotation on spring
    @QueryParam("name")
    private String name;
    @QueryParam("brand")
    private String brand;
}
