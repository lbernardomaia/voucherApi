package com.voucher.api.v1.core.expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidationException extends HttpClientErrorException {

    public ValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
