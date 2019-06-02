package com.voucher.api.v1.infrastructure.service.voucher;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.voucher.api.v1.core.model.Voucher;
import com.voucher.api.v1.core.service.voucher.CreateVoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger LOG = LoggerFactory.getLogger(CreateVoucherServiceImpl.class);

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
    @HystrixCommand(fallbackMethod = "createFallback")
    public Voucher create(Voucher voucher) {
        String voucherEndpoint = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(businessId, endpoint).toUriString();

        HttpEntity<Voucher> request = new HttpEntity<>(voucher);

        LOG.info("Call the API {}", voucherEndpoint);
        LOG.info("Request {}", request);
        ResponseEntity<Voucher> response = restTemplate.exchange(voucherEndpoint, HttpMethod.POST, request, Voucher.class);

        Voucher newVoucher = response.getBody();
        LOG.info("Voucher created {}", newVoucher);

        return newVoucher;
    }

    public Voucher createFallback(Voucher voucher) {
        LOG.warn("Calling createFallback");
        return new Voucher();
    }
}
