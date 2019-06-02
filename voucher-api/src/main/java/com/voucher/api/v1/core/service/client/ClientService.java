package com.voucher.api.v1.core.service.client;

import com.voucher.api.v1.core.dto.client.ClientDto;
import com.voucher.api.v1.core.dto.client.SearchClientDto;
import com.voucher.api.v1.core.mapper.ClientMapper;
import com.voucher.api.v1.core.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClientService {

    private static Logger LOG = LoggerFactory.getLogger(ClientService.class);

    private GetClientByIdService getClientByIdService;
    private SearchByClientService searchByClientService;
    private ClientMapper clientMapper;

    @Autowired
    ClientService(GetClientByIdService getClientByIdService,
                         SearchByClientService searchByClientService,
                         ClientMapper clientMapper) {
        this.getClientByIdService = getClientByIdService;
        this.searchByClientService = searchByClientService;
        this.clientMapper = clientMapper;
    }

    public ClientDto getClientById(String clientId){
        final Client client = getClientByIdService.search(clientId);
        LOG.info("Client {}", client);

        return clientMapper.mapToDto(client);
    }

    public List<ClientDto> searchBy(SearchClientDto searchClientDto) {
        Client client = clientMapper.mapToModel(searchClientDto);

        final Collection<Client> clients = searchByClientService.search(client);
        LOG.info("Found {} Clients", clients.size());

        return clientMapper.mapToDto(clients);
    }
}
