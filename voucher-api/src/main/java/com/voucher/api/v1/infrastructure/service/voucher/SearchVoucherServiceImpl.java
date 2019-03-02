package com.voucher.api.v1.infrastructure.service.voucher;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.voucher.api.v1.core.model.Voucher;
import com.voucher.api.v1.infrastructure.service.PagedResourcesSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Service
public class SearchVoucherServiceImpl implements SearchVoucherService {

    private static Logger LOG = LoggerFactory.getLogger(SearchVoucherServiceImpl.class);

    @Value("${uri}")
    private String uri;

    @Value("${voucher.endpoint}")
    private String endpoint;

    @Value("${businessId}")
    private String businessId;

    private SearchVoucherQueryParameter searchVoucherQueryParameter;

    private PagedResourcesSearch<Voucher> pagedResource;

    @Autowired
    public SearchVoucherServiceImpl(SearchVoucherQueryParameter searchVoucherQueryParameter,
                                    PagedResourcesSearch<Voucher> pagedResourcesSearch) {
        this.searchVoucherQueryParameter = searchVoucherQueryParameter;
        this.pagedResource = pagedResourcesSearch;
    }

    @Override
    @HystrixCommand(fallbackMethod = "searchFallback")
    public List<Voucher> search(final Voucher voucher) {
        if (voucher == null){
            return Collections.emptyList();
        }

        return pagedResource.search(voucherUri(voucher));
    }

    private Supplier<UriComponentsBuilder> voucherUri(Voucher voucher) {
        return () -> create(voucher);
    }

    private UriComponentsBuilder create(Voucher voucher) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(businessId, endpoint);

        searchVoucherQueryParameter.getQueryParams(voucher).ifPresent(uriComponentsBuilder::queryParams);

        return  uriComponentsBuilder;
    }

    public List<Voucher> searchFallback(Voucher voucher) {
        LOG.warn("Calling searchFallback");
        return Collections.emptyList();
    }
}
