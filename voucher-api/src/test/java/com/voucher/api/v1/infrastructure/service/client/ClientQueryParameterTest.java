package com.voucher.api.v1.infrastructure.service.client;


import com.voucher.api.v1.core.model.Client;
import org.junit.Before;
import org.junit.Test;

import static com.voucher.api.v1.infrastructure.service.client.SearchByClientQueryParameter.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ClientQueryParameterTest {

    private SearchByClientQueryParameterImpl clientQueryParameter;

    @Before
    public void setUp() {
        clientQueryParameter = new SearchByClientQueryParameterImpl();
    }

    @Test
    public void givenClientNull_ShouldReturnEmpty() {
        assertFalse(clientQueryParameter.getQueryParams(null).isPresent());
    }

    @Test
    public void givenClientEmpty_ShouldReturnEmpty() {
        assertFalse(clientQueryParameter.getQueryParams(new Client()).isPresent());
    }

    @Test
    public void givenClient_WhenHasFirstName_ShouldContainFirstName() {
        Client client = Client.builder().firstName("Test").build();

        assertTrue(clientQueryParameter.getQueryParams(client).get().containsKey(FIRST_NAME));
    }

    @Test
    public void givenClient_WhenHasLastName_ShouldContainLastName() {
        Client client = Client.builder().lastName("Test").build();

        assertTrue(clientQueryParameter.getQueryParams(client).get().containsKey(LAST_NAME));
    }

    @Test
    public void givenClient_WhenHasEmail_ShouldContainEmail() {
        Client client = Client.builder().email("Test").build();

        assertTrue(clientQueryParameter.getQueryParams(client).get().containsKey(EMAIL));
    }

    @Test
    public void givenClient_WhenHasPhone_ShouldContainPhone() {
        Client client = Client.builder().phone("Test").build();

        assertTrue(clientQueryParameter.getQueryParams(client).get().containsKey(PHONE));
    }

    @Test
    public void givenClient_WhenHasAllFields_ShouldContainReturnFour() {
        Client client = Client.builder().firstName("Test").lastName("Test").phone("Test").email("Test").build();

        assert clientQueryParameter.getQueryParams(client).get().size() == 4;
    }

}

