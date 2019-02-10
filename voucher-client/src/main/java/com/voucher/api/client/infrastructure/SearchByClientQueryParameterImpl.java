package com.voucher.api.client.infrastructure;

import com.voucher.api.client.core.model.Client;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Service
public class SearchByClientQueryParameterImpl implements SearchByClientQueryParameter {

    @Override
    public Optional<MultiValueMap<String, String>> getQueryParams(Client client) {

        if (client == null){
            return empty();
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(EMAIL, client.getEmail());
        params.add(PHONE, client.getPhone());
        params.add(FIRST_NAME, client.getFirstName());
        params.add(LAST_NAME, client.getLastName());
        return of(params);

    }
}
