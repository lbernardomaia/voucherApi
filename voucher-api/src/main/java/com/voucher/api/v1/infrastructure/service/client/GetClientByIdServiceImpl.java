package com.voucher.api.v1.infrastructure.service.client;

import com.voucher.api.v1.core.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GetClientByIdServiceImpl implements GetClientByIdService {

    @Value("${uri}")
    private String uri;

    @Value("${client.endpoint}")
    private String endpoint;

    @Value("${pathVariable.businessId}")
    private String branchId;

    private RestTemplate restTemplate;

    @Autowired
    public GetClientByIdServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Client search(String clientId){
        String clientIdEndpoint = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(branchId, endpoint, clientId).toUriString();

        return restTemplate.exchange(clientIdEndpoint, HttpMethod.GET,null, Client.class).getBody();
    }
}
