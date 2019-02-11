package com.voucher.api.client.core.mapper;

import com.voucher.api.client.core.dto.VoucherDto;
import com.voucher.api.client.core.model.Voucher;
import com.voucher.api.client.mapper.VoucherMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class VoucherMapperTest {

    private VoucherMapper voucherMapper;

    @Before
    public void setUp() {
        voucherMapper = VoucherMapper.INSTANCE;
    }

    @Test
    public void givenVoucher_WhenHasClientId_ThenVoucherDtoHasClientId() {
        Voucher voucher = Voucher.builder().clientId("123").build();

        VoucherDto voucherDto = voucherMapper.mapToDto(voucher);

        assert voucher.getClientId().equals(voucherDto.getClientId());
    }

    @Test
    public void givenVoucher_WhenHasSerialNumber_ThenVoucherDtoHasSerialNumber() {
        Voucher voucher = Voucher.builder().serialNumber("123").build();

        VoucherDto voucherDto = voucherMapper.mapToDto(voucher);

        assert voucher.getSerialNumber().equals(voucherDto.getSerialNumber());
    }

    @Test
    public void givenVoucher_WhenHasBalance_ThenVoucherDtoHasBalance() {
        Voucher voucher = Voucher.builder().balance(12D).build();

        VoucherDto voucherDto = voucherMapper.mapToDto(voucher);

        assert voucher.getBalance().equals(voucherDto.getBalance());
    }

    @Test
    public void givenVoucherList_WhenMappedToDto_ThenVoucherDtoListHasTheSameSize() {
        List<Voucher> vouchers = Arrays.asList(Voucher.builder().build(), Voucher.builder().build(), Voucher.builder().build());

        List<VoucherDto> voucherDtoList = voucherMapper.mapToDto(vouchers);

        assert vouchers.size() == voucherDtoList.size();
    }

}
