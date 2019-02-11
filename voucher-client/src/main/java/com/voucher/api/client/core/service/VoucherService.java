package com.voucher.api.client.core.service;

import com.voucher.api.client.core.dto.VoucherDto;

import java.util.List;

public interface VoucherService {
    List<VoucherDto> search(String clientId, String serialNumber);

    VoucherDto create(String clientId, Double balance);
}
