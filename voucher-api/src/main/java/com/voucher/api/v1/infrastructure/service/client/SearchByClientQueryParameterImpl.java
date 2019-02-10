package com.voucher.api.v1.infrastructure.service.client;

import com.voucher.api.v1.core.model.Client;
import org.apache.commons.lang3.StringUtils;
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

        if (StringUtils.isNotBlank(client.getEmail())){
            params.add(EMAIL, client.getEmail());
        }

        if (StringUtils.isNotBlank(client.getPhone())){
            params.add(PHONE, client.getPhone());
        }

        if (StringUtils.isNotBlank(client.getFirstName())){
            params.add(FIRST_NAME, client.getFirstName());
        }

        if (StringUtils.isNotBlank(client.getLastName())){
            params.add(LAST_NAME, client.getLastName());
        }

        if (params.isEmpty()){
            return empty();
        }else{
            return of(params);
        }

    }

}
