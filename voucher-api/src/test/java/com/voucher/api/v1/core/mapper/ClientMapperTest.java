package com.voucher.api.v1.core.mapper;

import com.voucher.api.v1.core.dto.client.ClientDto;
import com.voucher.api.v1.core.dto.client.SearchClientDto;
import com.voucher.api.v1.core.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientMapperTest {

    private ClientMapper clientMapper;

    @Before
    public void setUp() {
        clientMapper = ClientMapper.INSTANCE;
    }

    @Test
    public void givenClientWithEmail_WhenMappedToDto_ThenClientDtoMatch() {
        Client client = getClient();

        ClientDto clientDto = clientMapper.mapToDto(client);

        assert clientDto.getEmail().equals(client.getEmail());
    }

    public Client getClient() {
        return Client.builder().email("Email Test").firstName("First Name Test").lastName("Last Name Test").phone("Phone Test").build();
    }

    @Test
    public void givenClientWithFirstName_WhenMappedToDto_ThenClientDtoMatch() {
        Client client = getClient();

        ClientDto clientDto = clientMapper.mapToDto(client);

        assert clientDto.getFirstName().equals(client.getFirstName());
    }

    @Test
    public void givenClientWithLastName_WhenMappedToDto_ThenClientDtoMatch() {
        Client client = getClient();

        ClientDto clientDto = clientMapper.mapToDto(client);

        assert clientDto.getLastName().equals(client.getLastName());
    }

    @Test
    public void givenClientWithPhone_WhenMappedToDto_ThenClientDtoMatch() {
        Client client = getClient();

        ClientDto clientDto = clientMapper.mapToDto(client);

        assert clientDto.getPhone().equals(client.getPhone());
    }

    @Test
    public void givenSearchClientDtoWithEmail_WhenMappedToModel_ThenClientMatch() {
        SearchClientDto searchClientDto = getSearchClientDto();

        Client client = clientMapper.mapToModel(searchClientDto);

        assert client.getEmail().equals(searchClientDto.getEmail());
    }

    public SearchClientDto getSearchClientDto() {
        return SearchClientDto.builder().email("Email Test").firstName("First Name Test").lastName("Last Name Test").phone("Phone Test").build();
    }

    @Test
    public void givenSearchClientDtoWithFirstName_WhenMappedToModel_ThenClientMatch() {
        SearchClientDto searchClientDto = getSearchClientDto();

        Client client = clientMapper.mapToModel(searchClientDto);

        assert client.getFirstName().equals(searchClientDto.getFirstName());
    }

    @Test
    public void givenSearchClientDtoWithLastName_WhenMappedToModel_ThenClientMatch() {
        SearchClientDto searchClientDto = getSearchClientDto();

        Client client = clientMapper.mapToModel(searchClientDto);

        assert client.getLastName().equals(searchClientDto.getLastName());
    }

    @Test
    public void givenSearchClientDtoWithPhone_WhenMappedToModel_ThenClientMatch() {
        SearchClientDto searchClientDto = getSearchClientDto();

        Client client = clientMapper.mapToModel(searchClientDto);

        assert client.getPhone().equals(searchClientDto.getPhone());
    }

    @Test
    public void givenClientList_WhenMappedToDto_ThenClientDtoListHasTheSameSize() {
        List<Client> clients = Arrays.asList(getClient(), getClient(), getClient());

        List<ClientDto> clientDtoList = clientMapper.mapToDto(clients);

        assert clients.size() == clientDtoList.size();
    }

}
