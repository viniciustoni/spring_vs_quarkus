package com.vag.product;

import com.vag.product.dto.ProductDto;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class ProductPrototype {

    public final ProductDto IPHONE_PRODUCT = ProductDto.builder()
            .brand("Apple")
            .name("IPhone 17")
            .unitPrice(BigDecimal.valueOf(5350.99))
            .build();

    public final ProductDto GOOGLE_PIXEL_PRODUCT = ProductDto.builder()
            .brand("Google")
            .name("Pixel 98")
            .unitPrice(BigDecimal.valueOf(5100.99))
            .build();
}
