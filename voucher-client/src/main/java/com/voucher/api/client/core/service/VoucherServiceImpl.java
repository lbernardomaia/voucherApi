package com.voucher.api.client.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voucher.api.client.core.model.Voucher;
import com.voucher.api.client.infrastructure.service.voucher.CreateVoucherService;
import com.voucher.api.client.infrastructure.service.voucher.SearchVoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Service
public class VoucherServiceImpl implements VoucherService {
    private static Logger LOG = LoggerFactory.getLogger(VoucherServiceImpl.class);

    private SearchVoucherService searchVoucherService;
    private CreateVoucherService createVoucherService;
    private ObjectMapper objectMapper;

    @Autowired
    public VoucherServiceImpl(SearchVoucherService searchVoucherService,
                              CreateVoucherService createVoucherService,
                              ObjectMapper objectMapper) {
        this.searchVoucherService = searchVoucherService;
        this.createVoucherService = createVoucherService;
        this.objectMapper = objectMapper;
    }

    @Override
    public String search(String clientId, String serialNumber) {
        Voucher voucher = new Voucher();
        voucher.setClientId(clientId);
        voucher.setSerialNumber(serialNumber);

        Collection<Voucher> search = searchVoucherService.search(voucher);

        return result(!search.isEmpty(), search);
    }

    private String result(boolean hasResult, Object target) {
        if (!hasResult){
            return "No result found";
        }else{
            return convertToJson(target).orElse("");
        }
    }

    private Optional<String> convertToJson(Object object) {
        try {
            return of(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }

        return empty();
    }

    @Override
    public String create(String clientId, Double balance) {
        LOG.info("Voucher to save clientId {} balance {}", clientId, balance);
        Voucher voucherSaved = createVoucherService.create(clientId, balance);
        LOG.info("Voucher saved {}", voucherSaved);

        return convertToJson(voucherSaved).orElse("");
    }
}
