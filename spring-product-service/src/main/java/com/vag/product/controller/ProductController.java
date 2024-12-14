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

// DIFF: It'll use annotation from org.springframework.web.bind.annotation.* On Quarkus we will use annotations from jakarta.ws.rs.*.
// On Spring we usually we use *Controller on Quarkus we use *Resource
@RestController
@RequestMapping(value = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // DIFF: in only one line we can provide all the info, however we must add the @RequestBody for proper mapping on post.
    // For beans validation we can handle it with only one single annotation @Validated and provide our group,
    // on Quarkus we need to add a second annotation: @ConvertGroup(to = ProductDto.InsertProductGroup.class)
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
    // DIFF: For bean params, we dont need to add any annotation, spring handle for us automatically.
    public List<ProductDto> searchProducts(ProductSearchDto productSearchDto) {
//        return productService.searchProductByFilter(productSearchDto);
        return productService.searchProductByFilterSpecification(productSearchDto);
    }

}
