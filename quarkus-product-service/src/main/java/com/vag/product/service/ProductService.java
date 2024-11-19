package com.vag.product.service;

import com.vag.product.dto.ProductDto;
import com.vag.product.entity.ProductEntity;
import com.vag.product.exception.EntityNotFoundException;
import com.vag.product.mapper.ProductMapper;
import com.vag.product.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

// DIFF: @ApplicationScoped is similar to @Service / @Component / @Repository on spring. In general this means that it'll
// create a singleton bean
@ApplicationScoped
@RequiredArgsConstructor
public class ProductService {

    private static final String PRODUCT_NOT_FOUND_TEMPLATE_MESSAGE = "Product with id [%s] not found.";

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto saveProduct(ProductDto productDto) {

        ProductEntity product = productMapper.toProduct(productDto);
        productRepository.persist(product);

        return productMapper.toProductDto(product);
    }

    // DIFF: It's using @Transaction from jakarta, which has less options of configuration than spring,
    // for example:
    //      - we are not able to set the isolation level in here, it'll require us to set it manually: https://stackoverflow.com/questions/67486611/transaction-isolation-level-in-transactional#67502191
    //      - we are not able to set the readOnly Option in here
    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        return productRepository.findByIdOptional(productDto.id())
                .map(product -> productMapper.updateProduct(product, productDto))
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_TEMPLATE_MESSAGE.formatted(productDto.id())));

    }

    public ProductDto findById(Long id) {
        return productRepository.findByIdOptional(id)
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_TEMPLATE_MESSAGE.formatted(id)));
    }

    public List<ProductDto> searchProductByFilter(String name, String brand) {
        // TODO: specification
        return null;
    }
}
