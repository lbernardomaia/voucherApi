package com.voucher.api.client.infrastructure;

import com.voucher.api.client.core.model.Client;

import java.util.Collection;

public interface SearchByClientService {
    Collection<Client> searchBy(Client client);
}
