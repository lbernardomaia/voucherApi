package com.voucher.api.v1.core.service;

import com.voucher.api.v1.core.dto.ClientDto;
import com.voucher.api.v1.core.dto.SearchClientDto;

import java.util.List;

public interface ClientService {
    ClientDto getClientById(String clientId);

    List<ClientDto> searchBy(SearchClientDto searchClientDto);
}
