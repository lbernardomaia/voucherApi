package com.voucher.api.v1.infrastructure.service.voucher;

import com.voucher.api.v1.core.model.Voucher;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public interface SearchVoucherQueryParameter {
    String CLIENT_ID = "clientId";
    String SERIAL_NUMBER = "serialNumber";

    Optional<MultiValueMap<String, String>> getQueryParams(Voucher voucher);
}
