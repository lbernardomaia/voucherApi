package com.voucher.api.v1.core.service.client;

import com.voucher.api.v1.core.model.Client;

import java.util.Collection;

public interface SearchByClientService {
    Collection<Client> search(Client client);
}
