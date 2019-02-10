package com.voucher.api.v1.infrastructure.service.voucher;

import com.voucher.api.v1.core.model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CreateVoucherServiceImpl implements CreateVoucherService {

    @Value("${uri}")
    private String uri;

    @Value("${voucher.endpoint}")
    private String endpoint;

    @Value("${businessId}")
    private String businessId;

    private RestTemplate restTemplate;

    @Autowired
    public CreateVoucherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Voucher create(Voucher voucher) {
        String voucherEndpoint = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(businessId, endpoint).toUriString();

        HttpEntity<Voucher> request = new HttpEntity<>(voucher);

        ResponseEntity<Voucher> response = restTemplate.exchange(voucherEndpoint, HttpMethod.POST, request, Voucher.class);

        return response.getBody();
    }
}
