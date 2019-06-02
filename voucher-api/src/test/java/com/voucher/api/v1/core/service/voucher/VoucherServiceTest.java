package com.voucher.api.v1.core.service.voucher;

import com.voucher.api.v1.core.dto.voucher.CreateVoucherDto;
import com.voucher.api.v1.core.dto.voucher.SearchVoucherDto;
import com.voucher.api.v1.core.dto.voucher.VoucherDto;
import com.voucher.api.v1.core.mapper.VoucherMapper;
import com.voucher.api.v1.core.model.Voucher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class VoucherServiceTest {

    private VoucherService voucherService;
    private CreateVoucherService createVoucherService;
    private SearchVoucherService searchVoucherService;
    private String branchId = "123";
    private String voucherId = "123";

    @Before
    public void setUp() {
        searchVoucherService = mock(SearchVoucherService.class);
        createVoucherService = mock(CreateVoucherService.class);
        VoucherMapper voucherMapper = VoucherMapper.INSTANCE;

        voucherService = new VoucherService(searchVoucherService, createVoucherService, voucherMapper, branchId, voucherId);
    }

    @Test
    public void givenSearchVoucher_whenHasNotResult_ThenReturnEmptyList() {
        List<VoucherDto> search = voucherService.search(new SearchVoucherDto());

        assert search.isEmpty();
    }

    @Test
    public void givenSearchVoucher_whenHasTwoVouchersResult_ThenReturnTwoVoucherDto() {
        Voucher voucher1 = Voucher.builder().clientId("123").build();
        Voucher voucher2 = Voucher.builder().clientId("1234").build();

        when(searchVoucherService.search(any(Voucher.class))).thenReturn(Arrays.asList(voucher1, voucher2));

        List<VoucherDto> search = voucherService.search(new SearchVoucherDto());

        assert search.size() == 2;
    }

    @Test
    public void givenNewVoucher_whenModelIsCreated_ThenVoucherHasClientId() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("123", 10d);

        voucherService.create(createVoucherDto);

        ArgumentCaptor<Voucher> voucherCaptor = ArgumentCaptor.forClass(Voucher.class);
        verify(createVoucherService, atLeastOnce()).create(voucherCaptor.capture());

        assert voucherCaptor.getValue().getClientId().equals(createVoucherDto.getClientId());
    }

    @Test
    public void givenNewVoucher_whenModelIsCreated_ThenVoucherHasOriginalBalance() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("123", 10d);

        voucherService.create(createVoucherDto);

        ArgumentCaptor<Voucher> voucherCaptor = ArgumentCaptor.forClass(Voucher.class);
        verify(createVoucherService, atLeastOnce()).create(voucherCaptor.capture());

        assert voucherCaptor.getValue().getOriginalBalance().equals(createVoucherDto.getBalance());
    }

    @Test
    public void givenNewVoucher_whenModelIsCreated_ThenVoucherHasBranchId() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("123", 10d);

        voucherService.create(createVoucherDto);

        ArgumentCaptor<Voucher> voucherCaptor = ArgumentCaptor.forClass(Voucher.class);
        verify(createVoucherService, atLeastOnce()).create(voucherCaptor.capture());

        assert voucherCaptor.getValue().getCreatingBranchId().equals(branchId);
    }

    @Test
    public void givenNewVoucher_whenModelIsCreated_ThenVoucherHasVoucherId() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("123", 10d);

        voucherService.create(createVoucherDto);

        ArgumentCaptor<Voucher> voucherCaptor = ArgumentCaptor.forClass(Voucher.class);
        verify(createVoucherService, atLeastOnce()).create(voucherCaptor.capture());

        assert voucherCaptor.getValue().getVoucherId().equals(voucherId);
    }

    @Test
    public void givenNewVoucher_whenModelIsCreated_ThenVoucherHasExpiryDate() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("123", 10d);

        voucherService.create(createVoucherDto);

        ArgumentCaptor<Voucher> voucherCaptor = ArgumentCaptor.forClass(Voucher.class);
        verify(createVoucherService, atLeastOnce()).create(voucherCaptor.capture());

        assert voucherCaptor.getValue().getExpiryDate() != null;
    }

    @Test
    public void givenNewVoucher_whenModelIsCreated_ThenVoucherHasIssueDate() {
        CreateVoucherDto createVoucherDto = new CreateVoucherDto("123", 10d);

        voucherService.create(createVoucherDto);

        ArgumentCaptor<Voucher> voucherCaptor = ArgumentCaptor.forClass(Voucher.class);
        verify(createVoucherService, atLeastOnce()).create(voucherCaptor.capture());

        assert voucherCaptor.getValue().getIssueDate() != null;
    }
}
