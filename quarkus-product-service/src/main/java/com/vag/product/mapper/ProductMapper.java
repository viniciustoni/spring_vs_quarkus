package com.vag.product.mapper;

import com.vag.product.dto.ProductDto;
import com.vag.product.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

// DIFF: On spring we should use spring, instead of CDI
@Mapper(componentModel = "cdi")
public interface ProductMapper {

    ProductEntity toProduct(ProductDto productDto);

    @InheritInverseConfiguration(name = "toProduct")
    ProductDto toProductDto(ProductEntity productEntity);

    @Mapping(target = "id", ignore = true)
    ProductEntity updateProduct(@MappingTarget ProductEntity productEntity, ProductDto productDto);

}
