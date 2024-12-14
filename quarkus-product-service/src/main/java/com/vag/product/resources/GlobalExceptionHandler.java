package com.vag.product.resources;

import com.vag.product.dto.ErrorResponseDto;
import com.vag.product.exception.EntityNotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@Slf4j
public class GlobalExceptionHandler {

    // DIFF: ON spring we have: @ExceptionHandler(MethodArgumentNotValidException.class),
    // notice that the method itself it's a bit more verbose since we are returning the Response.
    @ServerExceptionMapper(value = EntityNotFoundException.class)
    public Response handleEntityNotFoundException(EntityNotFoundException exception) {
        log.error("Entity not found error: {}", exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponseDto(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
