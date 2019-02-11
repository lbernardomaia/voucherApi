package com.voucher.api.client.core.service;

import com.voucher.api.client.core.model.Voucher;
import com.voucher.api.client.infrastructure.service.voucher.SearchVoucherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class VoucherServiceTest {

    @InjectMocks
    private VoucherServiceImpl voucherService;

    @Mock
    private SearchVoucherService searchVoucherService;

    @Before
    public void setUp(){
    }

    @Test
    public void givenSearchClientBy_WhenServiceReturnIsEmpty_ThenNoResultMessageIsReturned() {
        when(searchVoucherService.search(any(Voucher.class))).thenReturn(Collections.emptyList());

        String result = voucherService.search("", "");

        assertEquals("No result found", result);
    }
}
