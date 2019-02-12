package com.voucher.api.v1.core.service;

import com.voucher.api.v1.core.dto.client.ClientDto;
import com.voucher.api.v1.core.dto.client.SearchClientDto;
import com.voucher.api.v1.core.mapper.ClientMapper;
import com.voucher.api.v1.core.model.Client;
import com.voucher.api.v1.infrastructure.service.client.GetClientByIdService;
import com.voucher.api.v1.infrastructure.service.client.SearchByClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientServiceTest {

    private ClientService clientService;
    private GetClientByIdService getClientByIdService;
    private SearchByClientService searchByClientService;

    @Before
    public void setUp() {
        getClientByIdService = mock(GetClientByIdService.class);
        searchByClientService = mock(SearchByClientService.class);
        ClientMapper clientMapper = ClientMapper.INSTANCE;

        clientService = new ClientServiceImpl(getClientByIdService, searchByClientService, clientMapper);
    }

    @Test
    public void givenGetClientClient_whenHasNotResult_ThenReturnNull() {
        ClientDto client = clientService.getClientById("");

        assert client == null;
    }

    @Test
    public void givenGetClientClient_whenHasTwoClientsResult_ThenReturnTwoClientsDto() {
        Client clientExpected = Client.builder().clientId("123").build();

        when(getClientByIdService.search(anyString())).thenReturn(clientExpected);

        ClientDto client = clientService.getClientById(clientExpected.getClientId());

        assert clientExpected.getClientId().equals(client.getClientId());
    }

    @Test
    public void givenSearchClient_whenHasNotResult_ThenReturnEmptyList() {
        List<ClientDto> search = clientService.searchBy(new SearchClientDto());

        assert search.isEmpty();
    }

    @Test
    public void givenSearchClient_whenHasTwoClientsResult_ThenReturnTwoClientsDto() {
        Client client1 = Client.builder().clientId("123").build();
        Client client2 = Client.builder().clientId("1234").build();

        when(searchByClientService.search(any(Client.class))).thenReturn(Arrays.asList(client1, client2));

        List<ClientDto> search = clientService.searchBy(new SearchClientDto());

        assert search.size() == 2;
    }
}
