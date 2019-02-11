package com.voucher.api.client.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voucher.api.client.core.dto.SearchClientDto;
import com.voucher.api.client.core.model.Client;
import com.voucher.api.client.infrastructure.service.client.GetClientByIdService;
import com.voucher.api.client.infrastructure.service.client.SearchClientService;
import com.voucher.api.client.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Service
public class ClientServiceImpl implements ClientService{

    private static Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);

    private GetClientByIdService getClientByIdService;
    private SearchClientService searchByClientService;
    private ObjectMapper objectMapper;
    private ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(GetClientByIdService getClientByIdService,
                             ObjectMapper objectMapper,
                             SearchClientService searchByClientService,
                             ClientMapper clientMapper) {
        this.getClientByIdService = getClientByIdService;
        this.objectMapper = objectMapper;
        this.searchByClientService = searchByClientService;
        this.clientMapper = clientMapper;
    }

    @Override
    public String getClientById(String clientId) {
        final Client search = getClientByIdService.search(clientId);

        return result(search != null, search);
    }

    private String result(boolean hasResult, Object target) {
        if (!hasResult){
            return "No result found";
        }else{
            return convertToJson(target).orElse("");
        }
    }

    private Optional<String> convertToJson(Object search) {
        try {
            return of(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(search));
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }

        return empty();
    }

    @Override
    public String searchBy(SearchClientDto searchClientDto) {
        Client client = clientMapper.mapToModel(searchClientDto);

        Collection<Client> clients = searchByClientService.searchBy(client);

        return result(!clients.isEmpty(), clients);
    }
}
