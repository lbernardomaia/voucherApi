package com.voucher.api.client.infrastructure.service.voucher;

import com.voucher.api.client.core.model.Voucher;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public interface SearchVoucherQueryParameter {
    String CLIENT_ID = "clientId";
    String SERIAL_NUMBER = "serialNumber";

    Optional<MultiValueMap<String, String>> getQueryParams(Voucher voucher);
}
