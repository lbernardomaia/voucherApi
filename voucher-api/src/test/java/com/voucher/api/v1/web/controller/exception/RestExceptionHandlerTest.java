package com.voucher.api.v1.web.controller.exception;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.*;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestExceptionHandlerTest {

    private RestExceptionHandler restExceptionHandler;

    @Before
    public void setUp() {
        restExceptionHandler = new RestExceptionHandler();
    }

    @Test
    public void givenErrorList_WhenIsEmpty_ThenReturnEmpty() {
        BindException bindException = mock(BindException.class);

        final List<String> errors = restExceptionHandler.errors(bindException);

        assert errors.isEmpty();
    }

    @Test
    public void givenErrorList_WhenHasFieldError_ThenReturnListWithFieldError() {
        String messageExpected = "clientId: Not Found";

        BindException bindException = mock(BindException.class);

        final FieldError fieldError = new FieldError("client", "clientId", "Not Found");
        when(bindException.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        final List<String> errors = restExceptionHandler.errors(bindException);

        assert errors.get(0).equals(messageExpected);
    }

    @Test
    public void givenErrorList_WhenHasGlobalError_ThenReturnListWithGlobalError() {
        String messageExpected = "Not Found";

        BindException bindException = mock(BindException.class);

        final ObjectError objectError = new ObjectError("", "Not Found");
        when(bindException.getGlobalErrors()).thenReturn(Collections.singletonList(objectError));

        final List<String> errors = restExceptionHandler.errors(bindException);

        assert errors.get(0).equals(messageExpected);
    }

    @Test
    public void givenErrorList_WhenHasFieldErrorAndGlobalError_ThenListSizeIsTwo() {
        BindException bindException = mock(BindException.class);

        final FieldError fieldError = new FieldError("client", "clientId", "Not Found");
        when(bindException.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        final ObjectError objectError = new ObjectError("", "Not Found");
        when(bindException.getGlobalErrors()).thenReturn(Collections.singletonList(objectError));

        final List<String> errors = restExceptionHandler.errors(bindException);

        assert errors.size() == 2;
    }


}
