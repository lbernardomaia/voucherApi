package com.voucher.api.client.infrastructure.service.voucher;

import com.voucher.api.client.core.dto.CreateVoucherDto;
import com.voucher.api.client.core.model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateVoucherServiceImpl implements CreateVoucherService {

    @Value("${voucherApi.v1.voucher}")
    private String voucherEndpoint;

    private RestTemplate restTemplate;

    @Autowired
    public CreateVoucherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Voucher create(String clientId, Double balance) {
        HttpEntity<CreateVoucherDto> request = new HttpEntity<>(new CreateVoucherDto(clientId, balance));

        ResponseEntity<Voucher> response = restTemplate.exchange(voucherEndpoint, HttpMethod.POST, request, Voucher.class);

        return response.getBody();
    }
}
