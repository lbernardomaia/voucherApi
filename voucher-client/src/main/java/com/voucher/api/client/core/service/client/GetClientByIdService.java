package com.voucher.api.client.infrastructure.service.client;

import com.voucher.api.client.core.model.Client;

public interface GetClientByIdService {
    Client search(String clientId);
}
