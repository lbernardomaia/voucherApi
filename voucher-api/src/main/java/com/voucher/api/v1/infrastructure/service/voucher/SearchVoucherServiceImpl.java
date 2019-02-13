package com.voucher.api.v1.infrastructure.service.voucher;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.voucher.api.v1.core.model.Voucher;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
public class SearchVoucherServiceImpl implements SearchVoucherService {

    private static Logger LOG = LoggerFactory.getLogger(SearchVoucherServiceImpl.class);

    @Value("${uri}")
    private String uri;

    @Value("${voucher.endpoint}")
    private String endpoint;

    @Value("${businessId}")
    private String businessId;

    private RestTemplate restTemplate;

    private SearchVoucherQueryParameter searchVoucherQueryParameter;

    private PagedResourcesSearch<Voucher> pagedResource;

    @Autowired
    public SearchVoucherServiceImpl(RestTemplate restTemplate,
                                    SearchVoucherQueryParameter searchVoucherQueryParameter,
                                    PagedResourcesSearch<Voucher> pagedResourcesSearch) {
        this.restTemplate = restTemplate;
        this.searchVoucherQueryParameter = searchVoucherQueryParameter;
        this.pagedResource = pagedResourcesSearch;
    }

    @Override
    @HystrixCommand(fallbackMethod = "searchFallback")
    public List<Voucher> search(final Voucher voucher) {
        if (voucher == null){
            return Collections.emptyList();
        }

        Function<Long, ResponseEntity<PagedResources<Voucher>>> api = nextPage -> getPagedResourcesResponseEntity(voucher, nextPage);

        return pagedResource.search(api);
    }

    private ResponseEntity<PagedResources<Voucher>> getPagedResourcesResponseEntity(Voucher voucher, Long nextPage) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(businessId, endpoint);

        searchVoucherQueryParameter.getQueryParams(voucher).ifPresent(uriComponentsBuilder::queryParams);

        uriComponentsBuilder.queryParam("page", nextPage);

        String url = uriComponentsBuilder.toUriString();
        LOG.info("Call the API {}", url);
        return restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<PagedResources<Voucher>>() {});
    }

    public List<Voucher> searchFallback(Voucher voucher) {
        LOG.warn("Calling searchFallback");
        Voucher build = Voucher.builder().clientId("123").build();
        Voucher build2 = Voucher.builder().clientId("54674576").build();
        return Arrays.asList(build, build2);
    }
}
