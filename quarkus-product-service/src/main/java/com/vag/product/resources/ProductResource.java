package com.vag.product.resources;

import com.vag.product.domain.ProductService;
import com.vag.product.dto.ProductDto;
import com.vag.product.dto.ProductSearchDto;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Path("/api/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDto saveProduct(@Valid @ConvertGroup(to = ProductDto.InsertProductGroup.class) ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDto updateProduct(@Valid @ConvertGroup(to = ProductDto.UpdateProductGroup.class) ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDto findById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    // TODO: add relative one for spring
    public List<ProductDto> searchProducts(@BeanParam ProductSearchDto productSearchDto) {
        return productService.searchProductByFilter(productSearchDto);
    }

}
