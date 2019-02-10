package com.voucher.api.v1.core.service;

import com.voucher.api.v1.core.dto.client.ClientDto;
import com.voucher.api.v1.core.dto.client.SearchClientDto;

import java.util.List;

public interface ClientService {
    ClientDto getClientById(String clientId);

    List<ClientDto> searchBy(SearchClientDto searchClientDto);
}
