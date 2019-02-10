package com.voucher.api.v1.core.service;

import com.voucher.api.v1.core.dto.voucher.CreateVoucherDto;
import com.voucher.api.v1.core.dto.voucher.SearchVoucherDto;
import com.voucher.api.v1.core.dto.voucher.VoucherDto;

import java.util.List;

public interface VoucherService {

    List<VoucherDto> search(SearchVoucherDto searchVoucherDto);

    VoucherDto create(CreateVoucherDto createVoucherDto);
}
