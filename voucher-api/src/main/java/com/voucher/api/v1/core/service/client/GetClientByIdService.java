package com.voucher.api.v1.core.service.client;

import com.voucher.api.v1.core.model.Client;

public interface GetClientByIdService {
    Client search(String clientId);
}
