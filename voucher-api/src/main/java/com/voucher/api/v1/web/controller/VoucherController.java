package com.voucher.api.v1.web.controller;

import com.voucher.api.v1.core.dto.voucher.CreateVoucherDto;
import com.voucher.api.v1.core.dto.voucher.SearchVoucherDto;
import com.voucher.api.v1.core.dto.voucher.VoucherDto;
import com.voucher.api.v1.core.service.voucher.VoucherService;
import com.voucher.api.v1.web.validator.voucher.VoucherBalanceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VoucherController {

    private static Logger LOG = LoggerFactory.getLogger(VoucherController.class);
    private VoucherService voucherService;
    private VoucherBalanceValidator voucherBalanceValidator;

    @Autowired
    public VoucherController(VoucherService voucherService,
                             VoucherBalanceValidator voucherBalanceValidator) {
        this.voucherService = voucherService;
        this.voucherBalanceValidator = voucherBalanceValidator;
    }


    @InitBinder("createVoucherDto")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(voucherBalanceValidator);
    }

    @GetMapping("/v1/voucher")
    public List<VoucherDto> search(SearchVoucherDto searchVoucherDto){
        LOG.info("Search Voucher {}", searchVoucherDto);
        return voucherService.search(searchVoucherDto);
    }

    @PostMapping(value = "/v1/voucher")
    public VoucherDto create(@RequestBody @Valid CreateVoucherDto createVoucherDto){
        LOG.info("create voucher {}", createVoucherDto);
        return voucherService.create(createVoucherDto);
    }
}
