package com.vag.product.domain;

import com.vag.product.domain.specification.ProductSpecification;
import com.vag.product.dto.ProductDto;
import com.vag.product.dto.ProductSearchDto;
import com.vag.product.entity.ProductEntity;
import com.vag.product.exception.EntityNotFoundException;
import com.vag.product.mapper.ProductMapper;
import com.vag.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// DIFF: On quarkus we will need to use: @ApplicationScoped which is similar to @Service / @Component / @Repository on spring
// - Again, remember that all these annotation on spring are meta-data of @Component.
// By default, Services are singleton beans
@Service
@RequiredArgsConstructor
public class ProductService {

    private static final String PRODUCT_NOT_FOUND_TEMPLATE_MESSAGE = "Product with id [%s] not found.";

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {

        ProductEntity product = productMapper.toProduct(productDto);
        productRepository.save(product);

        return productMapper.toProductDto(product);
    }

    @Transactional
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        return productRepository.findById(productId)
                .map(product -> productMapper.updateProduct(product, productDto))
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_TEMPLATE_MESSAGE.formatted(productDto.id())));

    }

    // DIFF: It's using @Transaction from spring, which has more options of configuration than jakarta,
    // for example:
    //      - we are able to set the isolation level in here, it'll require us to set it manually: https://stackoverflow.com/questions/67486611/transaction-isolation-level-in-transactional#67502191
    //      - we are able to set the readOnly Option in here
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_TEMPLATE_MESSAGE.formatted(id)));
    }

    public List<ProductDto> searchProductByFilter(ProductSearchDto productSearchDto) {
        return productRepository.findByBrandAndName(productSearchDto.getName(), productSearchDto.getBrand())
                .stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    public List<ProductDto> searchProductByFilterSpecification(ProductSearchDto productSearchDto) {

        var productSearch = Specification.where(ProductSpecification.activeProducts())
                .and(ProductSpecification.brand(productSearchDto.getBrand()))
                .and(ProductSpecification.name(productSearchDto.getName()));

        return productRepository.findAll(productSearch)
                .stream()
                .map(productMapper::toProductDto)
                .toList();
    }
}
