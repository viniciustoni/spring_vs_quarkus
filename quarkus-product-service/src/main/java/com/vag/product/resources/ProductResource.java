package com.vag.product.resources;

import com.vag.product.dto.ProductDto;
import com.vag.product.service.ProductService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("/api/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // TODO: add validation
    public ProductDto saveProduct(ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDto updateProduct(ProductDto productDto) {
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
    public String searchProducts(@QueryParam("name") String name, @QueryParam("brand") String brand) {
        return "Hello from Quarkus REST";
    }

}
