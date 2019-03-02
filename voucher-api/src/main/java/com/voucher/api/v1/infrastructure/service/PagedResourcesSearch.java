package com.voucher.api.v1.infrastructure.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Component
public class PagedResourcesSearch<T> {

    private static Logger LOG = LoggerFactory.getLogger(PagedResourcesSearch.class);
    private RestTemplate restTemplate;

    @Autowired
    public PagedResourcesSearch(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<T> search(Supplier<UriComponentsBuilder> uriComponentsBuilder) {
        List<T> result = new ArrayList<>();

        long nextPage = 0L;

        Collection<T> responseBody;

        do {
            ResponseEntity<PagedResources<T>> responseEntity = getPagedResourcesResponse(uriComponentsBuilder.get(), nextPage);

            if (responseEntity.getBody() == null) {
                break;
            }

           responseBody = responseEntity.getBody().getContent();

            result.addAll(responseBody);
            ++nextPage;
        } while (!responseBody.isEmpty());

        return result;
    }

    private ResponseEntity<PagedResources<T>> getPagedResourcesResponse(UriComponentsBuilder uriComponentsBuilder, Long nextPage) {
        uriComponentsBuilder.queryParam("page", nextPage);

        String url = uriComponentsBuilder.toUriString();
        LOG.info("Call the API {}", url);
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<PagedResources<T>>() {
        });
    }
}
