package com.voucher.api.client.infrastructure;

import com.voucher.api.client.core.model.Client;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public interface SearchByClientQueryParameter {
    String EMAIL = "email";
    String PHONE = "phone";
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";

    Optional<MultiValueMap<String, String>> getQueryParams(Client client);
}
