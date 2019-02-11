package com.voucher.api.client.core.service;

import com.voucher.api.client.core.dto.VoucherDto;
import com.voucher.api.client.core.model.Voucher;
import com.voucher.api.client.infrastructure.service.voucher.SearchVoucherService;
import com.voucher.api.client.mapper.VoucherMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class VoucherServiceTest {

    @InjectMocks
    private VoucherServiceImpl voucherService;

    @Mock
    private SearchVoucherService searchVoucherService;

    @Mock
    private VoucherMapper voucherMapper;

    @Before
    public void setUp(){
    }

    @Test
    public void givenSearchClientBy_WhenServiceReturnIsEmpty_ThenReturnIsEmpty() {
        when(searchVoucherService.search(any(Voucher.class))).thenReturn(Collections.emptyList());

        final List<VoucherDto> search = voucherService.search("", "");

        assert search.isEmpty();
    }
}
