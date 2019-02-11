package com.voucher.api.client.core.service;

import com.voucher.api.client.core.dto.SearchClientDto;
import com.voucher.api.client.core.model.Client;
import com.voucher.api.client.infrastructure.service.client.GetClientByIdService;
import com.voucher.api.client.infrastructure.service.client.SearchClientService;
import com.voucher.api.client.mapper.ClientMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
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
    public void givenGetClient_WhenServiceReturnIsNull_ThenNoResultMessageIsReturned() {
        when(getClientByIdService.search(anyString())).thenReturn(null);

        String result = clientService.getClientById("G7cwfHokOtDorjqFMuI3tA");

        assertEquals("No result found", result);
    }

    @Test
    public void givenSearchClientBy_WhenServiceReturnIsEmpty_ThenNoResultMessageIsReturned() {
        when(searchByClientService.searchBy(any(Client.class))).thenReturn(Collections.emptyList());

        String result = clientService.searchBy(new SearchClientDto("", "", "", ""));

        assertEquals("No result found", result);
    }
}
