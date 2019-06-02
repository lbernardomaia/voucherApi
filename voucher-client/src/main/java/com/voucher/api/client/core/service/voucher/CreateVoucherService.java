package com.voucher.api.client.core.service.voucher;

import com.voucher.api.client.core.model.Voucher;

public interface CreateVoucherService {
    Voucher create(String clientId, Double balance);
}
