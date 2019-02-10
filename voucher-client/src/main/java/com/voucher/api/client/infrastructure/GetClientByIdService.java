package com.voucher.api.client.infrastructure;

import com.voucher.api.client.core.model.Client;

public interface GetClientByIdService {
    Client search(String clientId);
}
