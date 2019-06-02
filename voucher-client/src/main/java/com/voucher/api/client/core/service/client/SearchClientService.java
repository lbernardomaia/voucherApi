package com.voucher.api.client.infrastructure.service.client;

import com.voucher.api.client.core.model.Client;

import java.util.Collection;

public interface SearchClientService {
    Collection<Client> searchBy(Client client);
}
