package com.voucher.api.v1.infrastructure.service.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.voucher.api.v1.core.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GetClientByIdServiceImpl implements GetClientByIdService {

    private static Logger LOG = LoggerFactory.getLogger(GetClientByIdServiceImpl.class);

    @Value("${uri}")
    private String uri;

    @Value("${client.endpoint}")
    private String endpoint;

    @Value("${businessId}")
    private String businessId;

    private RestTemplate restTemplate;

    @Autowired
    public GetClientByIdServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(fallbackMethod = "searchFallback")
    public Client search(String clientId) {
        String clientIdEndpoint = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(businessId, endpoint, clientId).toUriString();

        LOG.info("Call the API {}", clientIdEndpoint);
        return restTemplate.exchange(clientIdEndpoint, HttpMethod.GET, null, Client.class).getBody();
    }

    public Client searchFallback(String clientId) {
        LOG.warn("Calling searchFallback");
        return new Client();
    }
}