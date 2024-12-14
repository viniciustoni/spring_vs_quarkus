package com.vag.product.resources;

import com.vag.product.domain.ProductService;
import com.vag.product.dto.ProductDto;
import com.vag.product.dto.ProductSearchDto;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;

// DIFF: It'll use annotation from jakarta.ws.rs.*. On Spring we will use Spring annotations.
// On Spring we usually we use *Controller on Quarkus we use *Resource
@Path("/api/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;


    // DIFF: On spring it's cleaner than on Quarkus, in here we will need to add 3 annotations instead of only one.
    // Also for Beans validation we will need to provide 2 annotation in case we want to validate using groups
    // one for @Valid and one for the Group validation. For cases where we don't need group validation only
    // one annotation is needed.
    // But in other hands, we don't need to add @RequestBody for proper deserializable.
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveProduct(@Valid @ConvertGroup(to = ProductDto.InsertProductGroup.class) ProductDto productDto) {
        var newProduct = productService.saveProduct(productDto);
        return Response.status(Response.Status.CREATED)
                .entity(newProduct)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Long id, @Valid ProductDto productDto) {
        var updatedProduct = productService.updateProduct(id, productDto);
        return Response.status(Response.Status.OK)
                .entity(updatedProduct)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDto findById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    // DIFF: If we want to use request param inside a class, we will need to annotate with @BeanParam.
    // On spring we don't need any annotation
    public List<ProductDto> searchProducts(@BeanParam ProductSearchDto productSearchDto) {
//        return productService.searchProductByFilter(productSearchDto);
        return productService.searchProductByFilterSpecification(productSearchDto);
    }

}
