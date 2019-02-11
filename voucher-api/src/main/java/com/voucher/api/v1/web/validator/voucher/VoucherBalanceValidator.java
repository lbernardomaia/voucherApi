package com.voucher.api.v1.web.validator.voucher;

import com.voucher.api.v1.core.dto.voucher.CreateVoucherDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VoucherBalanceValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateVoucherDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateVoucherDto createVoucherDto = (CreateVoucherDto) target;

        if (createVoucherDto.getBalance() <= 0){
            errors.rejectValue("balance", "", "Balance is invalid");
        }
    }
}
