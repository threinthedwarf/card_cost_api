package com.etraveli.card_cost_api.controller;

import com.etraveli.card_cost_api.dto.FailureResponse;
import com.etraveli.card_cost_api.dto.ServiceResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BaseController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ServiceResponse handleAll(RuntimeException ex) {
        log.trace("Error occurred: ", ex);
        return new FailureResponse(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ServiceResponse handleValidationErrors(ConstraintViolationException ex) {
        log.trace("Validation error occurred: {}", ex.getMessage());
        String errorMessage = ex.getConstraintViolations().stream().findFirst().orElseThrow().getMessage();
        return new FailureResponse(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ServiceResponse handleValidationErrors(MethodArgumentNotValidException ex) {
        log.trace("Validation error occurred: {}", ex.getMessage());
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            String defaultMessage = fieldError.getDefaultMessage();
            errors.add(defaultMessage);
        }
        String errorMessage = errors.toString();
        return new FailureResponse(errorMessage);
    }

}
