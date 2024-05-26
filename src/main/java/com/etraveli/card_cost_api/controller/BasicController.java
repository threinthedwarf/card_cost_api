package com.etraveli.card_cost_api.controller;

import com.etraveli.card_cost_api.dto.FailureResponse;
import com.etraveli.card_cost_api.dto.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.stream.Collectors;

@Slf4j
public abstract class BasicController {
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ServiceResponse handleAll(Throwable ex) {
        log.trace("Error occurred: ", ex);
        return new FailureResponse(ex.getMessage());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ServiceResponse handleValidationErrors(WebExchangeBindException ex) {
        String errorMessage = ex.getAllErrors().stream()
                .map(this::getErrorMessage)
                .collect(Collectors.joining(", "));
        log.trace("Validation error occurred: {}", errorMessage);
        return new FailureResponse(errorMessage);
    }

    private String getErrorMessage(ObjectError error){
        return (error instanceof FieldError)
                ? ((FieldError) error).getField()+":"+ error.getDefaultMessage()
                : error.getDefaultMessage();
    }
}
