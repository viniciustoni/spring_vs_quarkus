package com.vag.product.dto;

import jakarta.ws.rs.QueryParam;
import lombok.Data;

@Data
public class ProductSearchDto {
    @QueryParam("name")
    private String name;
    @QueryParam("brand")
    private String brand;
}
