package com.voucher.api.client.infrastructure.service.voucher;

import com.voucher.api.client.core.model.Voucher;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Service
public class SearchVoucherQueryParameterImpl implements SearchVoucherQueryParameter {
    @Override
    public Optional<MultiValueMap<String, String>> getQueryParams(Voucher voucher) {

        if (voucher == null){
            return empty();
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(CLIENT_ID, voucher.getClientId());
        params.add(SERIAL_NUMBER, voucher.getSerialNumber());

        if (params.isEmpty()){
            return empty();
        }else{
            return of(params);
        }
    }
}
