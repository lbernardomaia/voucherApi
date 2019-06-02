package com.voucher.api.v1.infrastructure.service.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.voucher.api.v1.core.model.Client;
import com.voucher.api.v1.core.service.client.SearchByClientService;
import com.voucher.api.v1.infrastructure.service.PagedResourcesSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

@Service
public class SearchByClientServiceImpl implements SearchByClientService {

    private static Logger LOG = LoggerFactory.getLogger(SearchByClientServiceImpl.class);

    @Value("${uri}")
    private String uri;

    @Value("${client.endpoint}")
    private String endpoint;

    @Value("${businessId}")
    private String businessId;

    private SearchByClientQueryParameter searchByClientQueryParameter;

    private PagedResourcesSearch<Client> pagedResource;

    @Autowired
    public SearchByClientServiceImpl(SearchByClientQueryParameter searchByClientQueryParameter,
                                     PagedResourcesSearch<Client> pagedResourcesSearch) {
        this.searchByClientQueryParameter = searchByClientQueryParameter;
        this.pagedResource = pagedResourcesSearch;
    }

    @Override
    @HystrixCommand(fallbackMethod = "searchFallback")
    public Collection<Client> search(final Client client){
        if (client == null){
            return Collections.emptyList();
        }

        return pagedResource.search(clientUri(client));
    }


    private Supplier<UriComponentsBuilder> clientUri(Client client) {
        return () -> create(client);
    }

    private UriComponentsBuilder create(Client client) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(businessId, endpoint);
        searchByClientQueryParameter.getQueryParams(client).ifPresent(uriComponentsBuilder::queryParams);
        return uriComponentsBuilder;
    }

    public Collection<Client> searchFallback(Client client) {
        LOG.warn("Calling searchFallback");
        return Collections.emptyList();
    }
}
