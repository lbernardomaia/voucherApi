package com.voucher.api.client.core.service;

public interface VoucherService {
    String search(String clientId, String serialNumber);

    String create(String clientId, Double balance);
}
