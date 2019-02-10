package com.voucher.api.client.infrastructure;

import com.voucher.api.client.core.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GetClientByIdServiceImpl implements GetClientByIdService {

    @Value("${voucher.api.v1.client}")
    private String clientEndpoint;

    private RestTemplate restTemplate;

    @Autowired
    public GetClientByIdServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Client search(String clientId){
        String clientIdEndpoint = UriComponentsBuilder.fromHttpUrl(clientEndpoint).pathSegment(clientId).toUriString();

        return restTemplate.exchange(clientIdEndpoint, HttpMethod.GET,null, Client.class).getBody();
    }
}
