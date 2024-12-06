package com.vag.product.controller;

import com.vag.product.domain.ProductService;
import com.vag.product.dto.ProductDto;
import com.vag.product.dto.ProductSearchDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> saveProduct(@Validated(ProductDto.InsertProductGroup.class) @RequestBody ProductDto productDto) {
        var newProduct = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        var updatedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping
    // TODO: add relative one for spring
    public List<ProductDto> searchProducts(ProductSearchDto productSearchDto) {
//        return productService.searchProductByFilter(productSearchDto);
        return productService.searchProductByFilterSpecification(productSearchDto);
    }

}
