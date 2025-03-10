package com.courier.tracking.advice;

import com.courier.tracking.enums.Errors;
import com.courier.tracking.exception.NotFoundException;
import com.courier.tracking.model.CustomResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    CustomResponseEntity handleNotFoundException(NotFoundException ex) {
        return new CustomResponseEntity(Errors.ERROR_NO_ELEMENT.getValue(), ex.getMessage());
    }
}
