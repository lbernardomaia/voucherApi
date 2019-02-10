package com.voucher.api.v1.infrastructure.service.voucher;


import com.voucher.api.v1.core.model.Voucher;
import org.junit.Before;
import org.junit.Test;

import static com.voucher.api.v1.infrastructure.service.voucher.SearchVoucherQueryParameter.CLIENT_ID;
import static com.voucher.api.v1.infrastructure.service.voucher.SearchVoucherQueryParameter.SERIAL_NUMBER;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class SearchVoucherQueryParameterTest {

    private SearchVoucherQueryParameter searchVoucherQueryParameter;

    @Before
    public void setUp() {
        searchVoucherQueryParameter = new SearchVoucherQueryParameterImpl();
    }

    @Test
    public void givenVoucherNull_ShouldReturnEmpty() {
        assertFalse(searchVoucherQueryParameter.getQueryParams(null).isPresent());
    }

    @Test
    public void givenVoucherEmpty_ShouldReturnEmpty() {
        assertFalse(searchVoucherQueryParameter.getQueryParams(new Voucher()).isPresent());
    }

    @Test
    public void givenVoucher_WhenHasClientId_ShouldContainClientId() {
        Voucher voucher = Voucher.builder().clientId("Test").build();

        assertTrue(searchVoucherQueryParameter.getQueryParams(voucher).get().containsKey(CLIENT_ID));
    }

    @Test
    public void givenVoucher_WhenHasSerialNumber_ShouldContainSerialNumber() {
        Voucher voucher = Voucher.builder().serialNumber("Test").build();

        assertTrue(searchVoucherQueryParameter.getQueryParams(voucher).get().containsKey(SERIAL_NUMBER));
    }
}

