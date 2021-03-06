package com.voucher.api.client.core.service.client;

import com.voucher.api.client.core.dto.ClientDto;
import com.voucher.api.client.core.dto.SearchClientDto;
import com.voucher.api.client.core.model.Client;
import com.voucher.api.client.infrastructure.service.client.GetClientByIdService;
import com.voucher.api.client.infrastructure.service.client.SearchClientService;
import com.voucher.api.client.core.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClientService{

    private static Logger LOG = LoggerFactory.getLogger(ClientService.class);

    private GetClientByIdService getClientByIdService;
    private SearchClientService searchByClientService;
    private ClientMapper clientMapper;

    @Autowired
    public ClientService(GetClientByIdService getClientByIdService,
                             SearchClientService searchByClientService,
                             ClientMapper clientMapper) {
        this.getClientByIdService = getClientByIdService;
        this.searchByClientService = searchByClientService;
        this.clientMapper = clientMapper;
    }

    public ClientDto getClientById(String clientId) {
        Client client = getClientByIdService.search(clientId);
        LOG.info("Client {}", client);

        return clientMapper.mapToDto(client);
    }

    public List<ClientDto> searchBy(SearchClientDto searchClientDto) {
        Client client = clientMapper.mapToModel(searchClientDto);

        Collection<Client> clients = searchByClientService.searchBy(client);
        LOG.info("Found {} clients", clients.size());

        return clientMapper.mapToDto(clients);
    }
}
