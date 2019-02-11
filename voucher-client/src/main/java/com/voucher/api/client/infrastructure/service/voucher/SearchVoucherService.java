package com.voucher.api.client.infrastructure.service.voucher;

import com.voucher.api.client.core.model.Voucher;

import java.util.Collection;

public interface SearchVoucherService {
    Collection<Voucher> search(Voucher voucher);
}
