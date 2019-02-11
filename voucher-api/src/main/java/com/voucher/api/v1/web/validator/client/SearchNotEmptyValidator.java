package com.voucher.api.v1.web.validator.client;

import com.voucher.api.v1.core.dto.client.SearchClientDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SearchNotEmptyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SearchClientDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SearchClientDto searchClientDto = (SearchClientDto) target;

        if (StringUtils.isBlank(searchClientDto.getEmail()) && StringUtils.isBlank(searchClientDto.getFirstName())
                    && StringUtils.isBlank(searchClientDto.getLastName()) && StringUtils.isBlank(searchClientDto.getPhone())){
            errors.reject("searchClient","No valid parameters");
        }
    }
}
