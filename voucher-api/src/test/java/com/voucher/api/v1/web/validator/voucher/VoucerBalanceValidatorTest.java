package com.voucher.api.v1.web.validator.voucher;

import com.voucher.api.v1.core.dto.voucher.CreateVoucherDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class VoucerBalanceValidatorTest {

    private VoucherBalanceValidator voucherBalanceValidator;

    @Before
    public void setUp() throws Exception {
        voucherBalanceValidator = new VoucherBalanceValidator();
    }

    @Test
    public void givenBalance_WhenIsNegative_ThenHasBalanceError() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("", -1d);

        Errors errors = new BindException(createVoucherDto, "createVoucherDto");
        ValidationUtils.invokeValidator(voucherBalanceValidator, createVoucherDto, errors);

        assert errors.hasFieldErrors("balance");
    }

    @Test
    public void givenBalance_WhenIsZero_ThenHasBalanceError() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("", 0d);

        Errors errors = new BindException(createVoucherDto, "createVoucherDto");
        ValidationUtils.invokeValidator(voucherBalanceValidator, createVoucherDto, errors);

        assert errors.hasFieldErrors("balance");
    }
    @Test
    public void givenBalance_WhenIsPositive_ThenHasNoBalanceError() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("", 1d);

        Errors errors = new BindException(createVoucherDto, "createVoucherDto");
        ValidationUtils.invokeValidator(voucherBalanceValidator, createVoucherDto, errors);

        assert !errors.hasErrors();
    }
}
