package com.voucher.api.v1.infrastructure.service.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.voucher.api.v1.core.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class SearchByClientServiceImpl implements SearchByClientService {

    private static Logger LOG = LoggerFactory.getLogger(SearchByClientServiceImpl.class);

    @Value("${uri}")
    private String uri;

    @Value("${client.endpoint}")
    private String endpoint;

    @Value("${businessId}")
    private String businessId;

    private RestTemplate restTemplate;

    private SearchByClientQueryParameter searchByClientQueryParameter;

    @Autowired
    public SearchByClientServiceImpl(RestTemplate restTemplate, SearchByClientQueryParameter searchByClientQueryParameter) {
        this.restTemplate = restTemplate;
        this.searchByClientQueryParameter = searchByClientQueryParameter;
    }

    @Override
    @HystrixCommand(fallbackMethod = "searchFallback")
    public Collection<Client> search(Client client){
        if (client == null){
            return Collections.emptyList();
        }

        List<Client> result = new ArrayList<>();

        long totalPage = 0L;
        long currentPage = 0L;

        while(currentPage == 0L || currentPage < totalPage) {
            UriComponentsBuilder uriComponentsBuilder = getUriComponentsBuilder(client, currentPage);

            ResponseEntity<PagedResources<Client>> clients = callClientEndpoint(uriComponentsBuilder);

            if (clients.getBody() == null){
                break;
            }

            if (totalPage == 0){
                PagedResources.PageMetadata metadata = clients.getBody().getMetadata();
                totalPage = metadata.getTotalPages();
            }

            ++currentPage;
            result.addAll(clients.getBody().getContent());
        }

        return result;
    }

    private ResponseEntity<PagedResources<Client>> callClientEndpoint(UriComponentsBuilder uriComponentsBuilder) {
        String url = uriComponentsBuilder.toUriString();
        LOG.info("Call the API {}", url);
        return restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<PagedResources<Client>>() {});
    }

    private UriComponentsBuilder getUriComponentsBuilder(Client client, long currentPage) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(businessId, endpoint);

        searchByClientQueryParameter.getQueryParams(client).ifPresent(uriComponentsBuilder::queryParams);

        uriComponentsBuilder.queryParam("page", currentPage);
        return uriComponentsBuilder;
    }

    public Collection<Client> searchFallback(Client client) {
        LOG.warn("Calling searchFallback");
        return Collections.emptyList();
    }
}
