package com.voucher.api.v1.infrastructure.service.voucher.integration.external;

import com.voucher.api.v1.core.model.Voucher;
import com.voucher.api.v1.core.service.voucher.SearchVoucherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchVoucherServiceIT {

    @Autowired
    private SearchVoucherService searchVoucherService;

    @Test
    public void givenVoucherEndpoint_WhenClientIsNull_ShouldReturnEmpty() {
        Collection<Voucher> vouchers = searchVoucherService.search(null);

        assert vouchers.isEmpty();
    }

    @Test
    public void givenVoucherEndpoint_WhenHasClientId_ShouldReturnSomething() {
        Voucher voucher = Voucher.builder().clientId("WwEaIb0m4bhJphVtm2VgIw").build();

        Collection<Voucher> vouchers = searchVoucherService.search(voucher);

        assert !vouchers.isEmpty();
    }

    @Test
    public void givenVoucherEndpoint_WhenHasSerialNumber_ShouldReturnSomething() {
        Voucher voucher = Voucher.builder().serialNumber("11205").build();

        Collection<Voucher> vouchers = searchVoucherService.search(voucher);

        assert !vouchers.isEmpty();
    }
}
