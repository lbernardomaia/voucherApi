package com.voucher.api.client.infrastructure;

import com.voucher.api.client.core.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;

@Service
public class SearchByClientServiceImpl implements SearchByClientService {

    @Value("${voucher.api.v1.client}")
    private String clientEndpoint;

    private RestTemplate restTemplate;

    private SearchByClientQueryParameter searchByClientQueryParameter;

    @Autowired
    public SearchByClientServiceImpl(RestTemplate restTemplate, SearchByClientQueryParameter searchByClientQueryParameter) {
        this.restTemplate = restTemplate;
        this.searchByClientQueryParameter = searchByClientQueryParameter;
    }


    @Override
    public Collection<Client> searchBy(Client client) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(clientEndpoint);
        uriComponentsBuilder.queryParams(searchByClientQueryParameter.getQueryParams(client).orElse(new LinkedMultiValueMap<>()));

        ResponseEntity<List<Client>> responseEntity = restTemplate.exchange(uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Client>>() {
                });

        return responseEntity.getBody();
    }
}
