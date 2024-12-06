package com.vag.product.controller;

import com.vag.product.dto.ErrorResponseDto;
import com.vag.product.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto handleEntityNotFoundException(EntityNotFoundException exception) {
        log.error("Entity not found error: {}", exception.getMessage());
        return new ErrorResponseDto(exception.getMessage());
    }
}
