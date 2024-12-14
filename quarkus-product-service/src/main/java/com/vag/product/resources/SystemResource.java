package com.vag.product.resources;

import com.vag.product.domain.SystemService;
import com.vag.product.properties.ProductServiceProperties;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Path("/api/system")
@RequiredArgsConstructor
public class SystemResource {

    private final SystemService systemService;

    @GET
    public ProductServiceProperties getApplicationProperties() {
        return systemService.getProductServiceProperties();
    }

    @GET
    @Path("/timestamp")
    public ZonedDateTime getTimestamp() {
        return systemService.getTimestamp();
    }

    @GET
    @Path("/version")
    public String getCurrentVersion() {
        return systemService.getCurrentVersion();
    }
}
