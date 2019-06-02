package com.voucher.api.client.core.service.voucher;

import com.voucher.api.client.core.dto.VoucherDto;
import com.voucher.api.client.core.mapper.VoucherMapper;
import com.voucher.api.client.core.model.Voucher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class VoucherService {
    private static Logger LOG = LoggerFactory.getLogger(VoucherService.class);

    private SearchVoucherService searchVoucherService;
    private CreateVoucherService createVoucherService;
    private VoucherMapper voucherMapper;

    @Autowired
    public VoucherService(SearchVoucherService searchVoucherService,
                              CreateVoucherService createVoucherService,
                              VoucherMapper voucherMapper) {
        this.searchVoucherService = searchVoucherService;
        this.createVoucherService = createVoucherService;
        this.voucherMapper = voucherMapper;
    }

    public List<VoucherDto> search(String clientId, String serialNumber) {
        Voucher voucher = new Voucher();
        voucher.setClientId(clientId);
        voucher.setSerialNumber(serialNumber);

        Collection<Voucher> search = searchVoucherService.search(voucher);
        LOG.info("Found {} Vouchers", search.size());


        return voucherMapper.mapToDto(search);
    }

    public VoucherDto create(String clientId, Double balance) {
        LOG.info("Voucher to save clientId {} balance {}", clientId, balance);

        Voucher voucherSaved = createVoucherService.create(clientId, balance);

        LOG.info("Voucher saved {}", voucherSaved);

        return voucherMapper.mapToDto(voucherSaved);
    }
}
