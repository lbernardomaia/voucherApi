package com.voucher.api.v1.core.service;

import com.voucher.api.v1.core.dto.client.SearchClientDto;
import com.voucher.api.v1.core.expection.ValidationException;
import com.voucher.api.v1.core.mapper.ClientMapper;
import com.voucher.api.v1.infrastructure.service.client.SearchByClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private SearchByClientService searchByClientService;

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = ValidationException.class)
    public void givenClientSearch_WhenDtoIsNull_ThenThrowValidationException() {
        clientService.searchBy(null);
    }

    @Test(expected = ValidationException.class)
    public void givenClientSearch_WhenDtoIsEmpty_ThenThrowValidationException() {
        clientService.searchBy(new SearchClientDto());
    }

    @Test
    public void givenClientSearch_WhenDtoIsNotEmpty_ThenNoValidationExceptionIsThrown() {
        SearchClientDto searchClientDto = SearchClientDto.builder().email("Test").build();

        clientService.searchBy(searchClientDto);
    }
}

