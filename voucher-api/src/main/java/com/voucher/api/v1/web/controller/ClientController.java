package com.voucher.api.v1.web.controller;

import com.voucher.api.v1.core.service.ClientService;
import com.voucher.api.v1.core.dto.client.ClientDto;
import com.voucher.api.v1.core.dto.client.SearchClientDto;
import com.voucher.api.v1.web.validator.client.SearchNotEmptyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    private static Logger LOG = LoggerFactory.getLogger(ClientController.class);
    private ClientService clientService;
    private SearchNotEmptyValidator searchClientValidator;

    @Autowired
    public ClientController(ClientService clientService, SearchNotEmptyValidator searchClientValidator) {
        this.clientService = clientService;
        this.searchClientValidator = searchClientValidator;
    }

    @InitBinder("searchClientDto")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(searchClientValidator);
    }

    @GetMapping("/v1/client/{clientId}")
    public ClientDto getClient(@PathVariable String clientId){
        LOG.info("Getting Client by ID {}", clientId);
        return clientService.getClientById(clientId);
    }

    @GetMapping("/v1/client")
    public List<ClientDto> searchClient(@Valid SearchClientDto searchClientDto){
        LOG.info("Searching for {}", searchClientDto);
        return clientService.searchBy(searchClientDto);
    }
}
