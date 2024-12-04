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

@Path("/api/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

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
    // TODO: add relative one for spring
    public List<ProductDto> searchProducts(@BeanParam ProductSearchDto productSearchDto) {
//        return productService.searchProductByFilter(productSearchDto);
        return productService.searchProductByFilterSpecification(productSearchDto);
    }

}
