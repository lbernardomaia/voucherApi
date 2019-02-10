package com.voucher.api.v1.core.service;

import com.voucher.api.v1.core.dto.ClientDto;
import com.voucher.api.v1.core.dto.SearchClientDto;
import com.voucher.api.v1.core.expection.ValidationException;
import com.voucher.api.v1.core.mapper.ClientMapper;
import com.voucher.api.v1.core.model.Client;
import com.voucher.api.v1.infrastructure.service.client.GetClientByIdService;
import com.voucher.api.v1.infrastructure.service.client.SearchByClientService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return clientMapper.mapToDto(getClientByIdService.search(clientId));
    }

    @Override
    public List<ClientDto> searchBy(SearchClientDto searchClientDto) {
        if (hasAllParameterEmpty(searchClientDto)){
            throw new ValidationException("No valid parameter");
        }

        Client client = clientMapper.mapToModel(searchClientDto);

        return clientMapper.mapToDto(searchByClientService.search(client));
    }

    private boolean hasAllParameterEmpty(SearchClientDto searchClientDto) {
        return searchClientDto == null || isEmpty(searchClientDto);
    }

    private boolean isEmpty(SearchClientDto searchClientDto) {
        return StringUtils.isBlank(searchClientDto.getEmail())
                && StringUtils.isBlank(searchClientDto.getFirstName())
                && StringUtils.isBlank(searchClientDto.getLastName())
                && StringUtils.isBlank(searchClientDto.getPhone());
    }
}
