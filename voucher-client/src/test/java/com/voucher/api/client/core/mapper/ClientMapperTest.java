package com.voucher.api.client.core.mapper;

import com.voucher.api.client.core.dto.SearchClientDto;
import com.voucher.api.client.core.model.Client;
import com.voucher.api.client.mapper.ClientMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientMapperTest {

    private ClientMapper clientMapper;

    @Before
    public void setUp() {
        clientMapper = ClientMapper.INSTANCE;
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
}
