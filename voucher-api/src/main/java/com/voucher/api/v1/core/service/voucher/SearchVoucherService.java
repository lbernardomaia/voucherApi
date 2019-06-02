package com.voucher.api.v1.core.service.voucher;

import com.voucher.api.v1.core.model.Voucher;

import java.util.List;

public interface SearchVoucherService {

    List<Voucher> search(Voucher voucher);
}