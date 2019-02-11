package com.voucher.api.v1.web.validator.client;

import com.voucher.api.v1.core.dto.client.SearchClientDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class SearchNotEmptyValidatorTest {

    private SearchNotEmptyValidator searchNotEmptyValidator;

    @Before
    public void setUp() throws Exception {
        searchNotEmptyValidator = new SearchNotEmptyValidator();
    }

    @Test
    public void giverSearch_WhenAllFieldsAreEmpty_ThenHasError() {
        SearchClientDto searchClientDto = new SearchClientDto("", "","","");

        Errors errors = new BindException(searchClientDto, "searchVoucherDto");
        ValidationUtils.invokeValidator(searchNotEmptyValidator, searchClientDto, errors);

        assert errors.hasErrors();
    }

    @Test
    public void giverSearch_WhenHasEmail_ThenHasNoError() {
        SearchClientDto searchClientDto = new SearchClientDto("Test", "","","");

        Errors errors = new BindException(searchClientDto, "searchVoucherDto");
        ValidationUtils.invokeValidator(searchNotEmptyValidator, searchClientDto, errors);

        assert !errors.hasErrors();
    }

    @Test
    public void giverSearch_WhenHasPhone_ThenHasNoError() {
        SearchClientDto searchClientDto = new SearchClientDto("", "Test","","");

        Errors errors = new BindException(searchClientDto, "searchVoucherDto");
        ValidationUtils.invokeValidator(searchNotEmptyValidator, searchClientDto, errors);

        assert !errors.hasErrors();
    }

    @Test
    public void giverSearch_WhenHasFirstName_ThenHasNoError() {
        SearchClientDto searchClientDto = new SearchClientDto("", "","Test","");

        Errors errors = new BindException(searchClientDto, "searchVoucherDto");
        ValidationUtils.invokeValidator(searchNotEmptyValidator, searchClientDto, errors);

        assert !errors.hasErrors();
    }

    @Test
    public void giverSearch_WhenHasLastName_ThenHasNoError() {
        SearchClientDto searchClientDto = new SearchClientDto("", "","","Test");

        Errors errors = new BindException(searchClientDto, "searchVoucherDto");
        ValidationUtils.invokeValidator(searchNotEmptyValidator, searchClientDto, errors);

        assert !errors.hasErrors();
    }

}
