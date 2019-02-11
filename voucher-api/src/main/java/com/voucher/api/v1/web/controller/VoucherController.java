package com.voucher.api.v1.web.controller;

import com.voucher.api.v1.core.dto.voucher.CreateVoucherDto;
import com.voucher.api.v1.core.dto.voucher.SearchVoucherDto;
import com.voucher.api.v1.core.dto.voucher.VoucherDto;
import com.voucher.api.v1.core.service.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoucherController {

    private static Logger LOG = LoggerFactory.getLogger(VoucherController.class);
    private VoucherService voucherService;

    @Autowired
    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/v1/voucher")
    public List<VoucherDto> search(SearchVoucherDto searchVoucherDto){
        LOG.info("Search Voucher {}", searchVoucherDto);
        return voucherService.search(searchVoucherDto);
    }

    @PostMapping(value = "/v1/voucher")
    public VoucherDto create(@RequestBody CreateVoucherDto createVoucherDto){
        LOG.info("create voucher {}", createVoucherDto);
        return voucherService.create(createVoucherDto);
    }
}
