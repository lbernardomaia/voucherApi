package com.voucher.api.client.infrastructure.service.voucher;

import com.voucher.api.client.core.model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;

@Service
public class SearchVoucherServiceImpl implements SearchVoucherService {

    @Value("${voucherApi.v1.voucher}")
    private String voucherEndpoint;

    private RestTemplate restTemplate;

    private SearchVoucherQueryParameter searchVoucherQueryParameter;

    @Autowired
    public SearchVoucherServiceImpl(RestTemplate restTemplate, SearchVoucherQueryParameter searchVoucherQueryParameter) {
        this.restTemplate = restTemplate;
        this.searchVoucherQueryParameter = searchVoucherQueryParameter;
    }

    @Override
    public Collection<Voucher> search(Voucher voucher) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(voucherEndpoint);
        uriComponentsBuilder.queryParams(searchVoucherQueryParameter.getQueryParams(voucher).orElse(new LinkedMultiValueMap<>()));

        ResponseEntity<List<Voucher>> responseEntity = restTemplate.exchange(uriComponentsBuilder.toUriString(),
                                                                            HttpMethod.GET,
                                                                            null,
                                                                            new ParameterizedTypeReference<List<Voucher>>() {
                                                                            });

        return responseEntity.getBody();
    }
}
