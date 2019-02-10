package com.voucher.api.client.core.service;

import com.voucher.api.client.core.dto.SearchClientDto;

public interface ClientService {
    String getClientById(String clientId);

    String searchBy(SearchClientDto searchClientDto);
}
