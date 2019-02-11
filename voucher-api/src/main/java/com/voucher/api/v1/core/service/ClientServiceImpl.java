package com.voucher.api.v1.core.service;

import com.voucher.api.v1.core.dto.client.ClientDto;
import com.voucher.api.v1.core.dto.client.SearchClientDto;
import com.voucher.api.v1.core.mapper.ClientMapper;
import com.voucher.api.v1.core.model.Client;
import com.voucher.api.v1.infrastructure.service.client.GetClientByIdService;
import com.voucher.api.v1.infrastructure.service.client.SearchByClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private static Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);

    private GetClientByIdService getClientByIdService;
    private SearchByClientService searchByClientService;
    private ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(GetClientByIdService getClientByIdService,
                             SearchByClientService searchByClientService,
                             ClientMapper clientMapper) {
        this.getClientByIdService = getClientByIdService;
        this.searchByClientService = searchByClientService;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientDto getClientById(String clientId){
        final Client client = getClientByIdService.search(clientId);
        LOG.info("Client {}", client);

        return clientMapper.mapToDto(client);
    }

    @Override
    public List<ClientDto> searchBy(SearchClientDto searchClientDto) {
        Client client = clientMapper.mapToModel(searchClientDto);

        final Collection<Client> clients = searchByClientService.search(client);
        LOG.info("Found {} Clients", clients.size());

        return clientMapper.mapToDto(clients);
    }
}
