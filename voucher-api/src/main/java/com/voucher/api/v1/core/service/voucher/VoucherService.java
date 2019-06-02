package com.voucher.api.v1.core.service.voucher;

import com.voucher.api.v1.core.dto.voucher.CreateVoucherDto;
import com.voucher.api.v1.core.dto.voucher.SearchVoucherDto;
import com.voucher.api.v1.core.dto.voucher.VoucherDto;
import com.voucher.api.v1.core.mapper.VoucherMapper;
import com.voucher.api.v1.core.model.Voucher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoucherService {

    private static Logger LOG = LoggerFactory.getLogger(VoucherService.class);

    private SearchVoucherService searchVoucherService;
    private CreateVoucherService createVoucherService;
    private VoucherMapper voucherMapper;
    private String branchId;
    private String voucherId;

    @Autowired
    VoucherService(SearchVoucherService searchVoucherService,
                          CreateVoucherService createVoucherService,
                          VoucherMapper voucherMapper,
                          @Value("${branchId}") String branchId,
                          @Value("${voucherId}") String voucherId) {
        this.searchVoucherService = searchVoucherService;
        this.createVoucherService = createVoucherService;
        this.voucherMapper = voucherMapper;
        this.branchId = branchId;
        this.voucherId = voucherId;
    }

    public List<VoucherDto> search(SearchVoucherDto searchVoucherDto) {
        Voucher voucher = voucherMapper.mapToModel(searchVoucherDto);

        final List<Voucher> vouchers = searchVoucherService.search(voucher);
        LOG.info("Found {} Vouchers", vouchers.size());

        return voucherMapper.mapToDto(vouchers);
    }

    public VoucherDto create(CreateVoucherDto createVoucherDto) {
        Voucher voucher = voucherMapper.mapToModel(createVoucherDto);
        voucher.setVoucherId(voucherId);
        voucher.setCreatingBranchId(branchId);
        voucher.setExpiryDate(LocalDateTime.now());
        voucher.setIssueDate(LocalDateTime.now());

        LOG.info("Voucher to be saved {}", voucher);
        Voucher voucherSaved = createVoucherService.create(voucher);
        LOG.info("Voucher saved {}", voucherSaved);

        return voucherMapper.mapToDto(voucherSaved);
    }
}
