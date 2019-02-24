package com.voucher.api.v1.web.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}