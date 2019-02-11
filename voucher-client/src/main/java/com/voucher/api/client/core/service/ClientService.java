package com.voucher.api.client.core.service;

import com.voucher.api.client.core.dto.ClientDto;
import com.voucher.api.client.core.dto.SearchClientDto;

import java.util.List;

public interface ClientService {
    ClientDto getClientById(String clientId);

    List<ClientDto> searchBy(SearchClientDto searchClientDto);
}
