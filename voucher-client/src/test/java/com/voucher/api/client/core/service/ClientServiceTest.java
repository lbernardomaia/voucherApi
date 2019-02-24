package com.voucher.api.client.core.service;

import com.voucher.api.client.core.dto.ClientDto;
import com.voucher.api.client.core.dto.SearchClientDto;
import com.voucher.api.client.core.model.Client;
import com.voucher.api.client.infrastructure.service.client.GetClientByIdService;
import com.voucher.api.client.infrastructure.service.client.SearchClientService;
import com.voucher.api.client.core.mapper.ClientMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private GetClientByIdService getClientByIdService;

    @Mock
    private SearchClientService searchByClientService;

    @Mock
    private ClientMapper clientMapper;

    @Before
    public void setUp() {
        clientMapper = ClientMapper.INSTANCE;
    }

    @Test
    public void givenGetClient_WhenServiceReturnIsNull_ThenReturnNull() {
        when(getClientByIdService.search(anyString())).thenReturn(null);

        final String clientId = "G7cwfHokOtDorjqFMuI3tA";

        final ClientDto clientDto = clientService.getClientById(clientId);

        assert clientDto == null;
    }

    @Test
    public void givenSearchClientBy_WhenServiceReturnIsEmpty_ThenReturnEmpty() {
        when(searchByClientService.searchBy(any(Client.class))).thenReturn(Collections.emptyList());

        final List<ClientDto> clientDtos = clientService.searchBy(new SearchClientDto("", "", "", ""));

        assert clientDtos.isEmpty();
    }
}
