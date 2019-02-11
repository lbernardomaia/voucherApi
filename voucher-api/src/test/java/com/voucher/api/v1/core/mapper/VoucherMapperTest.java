package com.voucher.api.v1.core.mapper;

import com.voucher.api.v1.core.dto.voucher.CreateVoucherDto;
import com.voucher.api.v1.core.dto.voucher.SearchVoucherDto;
import com.voucher.api.v1.core.dto.voucher.VoucherDto;
import com.voucher.api.v1.core.model.Voucher;
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
    public void givenVoucher_WhenHasOriginalBalance_ThenVoucherDtoHasBalance() {
        Voucher voucher = Voucher.builder().originalBalance(12D).build();

        VoucherDto voucherDto = voucherMapper.mapToDto(voucher);

        assert voucher.getOriginalBalance().equals(voucherDto.getBalance());
    }

    @Test
    public void givenVoucherList_WhenMappedToDto_ThenVoucherDtoListHasTheSameSize() {
        List<Voucher> vouchers = Arrays.asList(Voucher.builder().build(), Voucher.builder().build(), Voucher.builder().build());

        List<VoucherDto> voucherDtoList = voucherMapper.mapToDto(vouchers);

        assert vouchers.size() == voucherDtoList.size();
    }

    @Test
    public void givenSearchVoucherDto_WhenHasClientId_ThenVoucherHasClientId() {
        SearchVoucherDto searchVoucherDto = new SearchVoucherDto("123","");

        Voucher voucher = voucherMapper.mapToModel(searchVoucherDto);

        assert searchVoucherDto.getClientId().equals(voucher.getClientId());
    }

    @Test
    public void givenSearchVoucherDto_WhenHasSerialNumber_ThenVoucherHasSerialNumber() {
        SearchVoucherDto searchVoucherDto = new SearchVoucherDto("","123");

        Voucher voucher = voucherMapper.mapToModel(searchVoucherDto);

        assert searchVoucherDto.getSerialNumber().equals(voucher.getSerialNumber());
    }

    @Test
    public void givenCreateVoucherDto_WhenHasClientId_ThenVoucherHasClientId() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("123",null);

        Voucher voucher = voucherMapper.mapToModel(createVoucherDto);

        assert createVoucherDto.getClientId().equals(voucher.getClientId());
    }

    @Test
    public void givenCreateVoucherDto_WhenHasBalance_ThenVoucherHasBalance() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("",10d);

        Voucher voucher = voucherMapper.mapToModel(createVoucherDto);

        assert createVoucherDto.getBalance().equals(voucher.getOriginalBalance());
    }

}
