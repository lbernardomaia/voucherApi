package com.voucher.api.client.infrastructure.service.client;

import com.voucher.api.client.core.model.Client;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

interface SearchByClientQueryParameter {
    String EMAIL = "email";
    String PHONE = "phone";
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";

    Optional<MultiValueMap<String, String>> getQueryParams(Client client);
}
