package com.voucher.api.v1.web.controller.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String VALIDATION_FAILED_MESSAGE = "Validation Failed";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> fieldErrors = errors(ex.getBindingResult());

        return handleExceptionInternal(ex, headers, request, fieldErrors);
    }

    List<String> errors(BindingResult bindingResult) {
        List<String> fieldErrors = fieldErrors(bindingResult.getFieldErrors());
        List<String> globalErrors = globalErrors(bindingResult.getGlobalErrors());

        fieldErrors.addAll(globalErrors);
        return fieldErrors;
    }

    private List<String> fieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream().map(error -> error.getField() + ": " + error.getDefaultMessage())
                                   .collect(Collectors.toList());
    }

    private List<String> globalErrors(List<ObjectError> globalError) {
        return globalError.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                                   .collect(Collectors.toList());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {
        List<String> fieldErrors = errors(ex.getBindingResult());

        return handleExceptionInternal(ex, headers, request, fieldErrors);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpHeaders headers, WebRequest request, List<String> fieldErrors) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, VALIDATION_FAILED_MESSAGE, fieldErrors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }
}