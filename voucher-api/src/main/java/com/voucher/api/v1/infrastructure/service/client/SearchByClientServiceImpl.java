package com.voucher.api.v1.infrastructure.service.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.voucher.api.v1.core.model.Client;
import com.voucher.api.v1.infrastructure.service.PagedResourcesSearch;
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

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

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

    private PagedResourcesSearch<Client> pagedResource;

    @Autowired
    public SearchByClientServiceImpl(RestTemplate restTemplate,
                                     SearchByClientQueryParameter searchByClientQueryParameter,
                                     PagedResourcesSearch<Client> pagedResourcesSearch) {
        this.restTemplate = restTemplate;
        this.searchByClientQueryParameter = searchByClientQueryParameter;
        this.pagedResource = pagedResourcesSearch;
    }

    @Override
    @HystrixCommand(fallbackMethod = "searchFallback")
    public Collection<Client> search(final Client client){
        if (client == null){
            return Collections.emptyList();
        }

        Function<Long, ResponseEntity<PagedResources<Client>>> api = nextPage -> getPagedResourcesResponseEntity(client, nextPage);

        return pagedResource.search(api);
    }

    private ResponseEntity<PagedResources<Client>> getPagedResourcesResponseEntity(Client client, Long nextPage) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(businessId, endpoint);

        searchByClientQueryParameter.getQueryParams(client).ifPresent(uriComponentsBuilder::queryParams);

        uriComponentsBuilder.queryParam("page", nextPage);

        String url = uriComponentsBuilder.toUriString();
        LOG.info("Call the API {}", url);
        return restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<PagedResources<Client>>() {});
    }

    public Collection<Client> searchFallback(Client client) {
        LOG.warn("Calling searchFallback");
        return Collections.emptyList();
    }
}
