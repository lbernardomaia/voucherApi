package com.voucher.api.v1.infrastructure.service.voucher;

import com.voucher.api.v1.core.model.Voucher;
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
import java.util.Collections;
import java.util.List;

@Service
public class SearchVoucherServiceImpl implements SearchVoucherService {

    @Value("${uri}")
    private String uri;

    @Value("${voucher.endpoint}")
    private String endpoint;

    @Value("${businessId}")
    private String branchId;

    private RestTemplate restTemplate;

    private SearchVoucherQueryParameter searchVoucherQueryParameter;

    @Autowired
    public SearchVoucherServiceImpl(RestTemplate restTemplate, SearchVoucherQueryParameter searchVoucherQueryParameter) {
        this.restTemplate = restTemplate;
        this.searchVoucherQueryParameter = searchVoucherQueryParameter;
    }

    @Override
    public List<Voucher> search(Voucher voucher) {
        if (voucher == null){
            return Collections.emptyList();
        }

        List<Voucher> result = new ArrayList<>();

        long totalPage = 0L;
        long currentPage = 0L;

        while(currentPage == 0L || currentPage < totalPage) {
            UriComponentsBuilder uriComponentsBuilder = getUriComponentsBuilder(voucher, currentPage);

            ResponseEntity<PagedResources<Voucher>> vouchers = callClientEndpoint(uriComponentsBuilder);

            if (vouchers.getBody() == null){
                break;
            }

            if (totalPage == 0){
                PagedResources.PageMetadata metadata = vouchers.getBody().getMetadata();
                totalPage = metadata.getTotalPages();
            }

            ++currentPage;
            result.addAll(vouchers.getBody().getContent());
        }

        return result;
    }

    private ResponseEntity<PagedResources<Voucher>> callClientEndpoint(UriComponentsBuilder uriComponentsBuilder) {
        return restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedResources<Voucher>>() {});
    }

    private UriComponentsBuilder getUriComponentsBuilder(Voucher voucher, long currentPage) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(branchId, endpoint);

        searchVoucherQueryParameter.getQueryParams(voucher).ifPresent(uriComponentsBuilder::queryParams);

        uriComponentsBuilder.queryParam("page", currentPage);
        return uriComponentsBuilder;
    }
}
