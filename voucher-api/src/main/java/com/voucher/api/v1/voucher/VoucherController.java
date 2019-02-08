package com.voucher.api.v1.voucher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class VoucherController {

    private static Logger LOG = LoggerFactory.getLogger(VoucherController.class);

    @GetMapping("/voucher")
    public List<String> getVoucher(){
        LOG.info("Voucher api");
        return Arrays.asList("L1234","L78765","L45634");
    }

}
