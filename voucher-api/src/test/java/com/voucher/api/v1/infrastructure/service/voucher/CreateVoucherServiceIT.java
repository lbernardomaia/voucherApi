package com.voucher.api.v1.infrastructure.service.voucher;

import com.voucher.api.v1.core.model.Voucher;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateVoucherServiceIT {

    @Autowired
    private CreateVoucherService createVoucherService;

    @Value("${branchId}")
    private String branchId;

    @Test
    public void givenVoucherEndpoint_WhenIsPost_ThenCreateVoucherAndReturnSerialNumber() {
        Voucher voucher = Voucher.builder().clientId("Nir6yiEEinni-2_47QWyUA")
                                           .creatingBranchId(branchId)
                                           .expiryDate(LocalDateTime.now())
                                           .issueDate(LocalDateTime.now())
                                           .originalBalance(123.12d)
                                           .voucherId("ab-123")
                                           .build();

        Voucher result = createVoucherService.create(voucher);

        assert StringUtils.isNotBlank(result.getSerialNumber());
    }
}