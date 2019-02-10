package com.voucher.api.v1.web.controller;

import com.voucher.api.v1.core.service.ClientService;
import com.voucher.api.v1.core.dto.ClientDto;
import com.voucher.api.v1.core.dto.SearchClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    private static Logger LOG = LoggerFactory.getLogger(ClientController.class);
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/v1/client/{clientId}")
    public ClientDto getClient(@PathVariable String clientId){
        LOG.info("Getting Client by ID {}", clientId);
        return clientService.getClientById(clientId);
    }

    @GetMapping("/v1/client")
    public List<ClientDto> searchClient(SearchClientDto searchClientDto){
        LOG.info("Searching for {}", searchClientDto);
        return clientService.searchBy(searchClientDto);
    }
}
