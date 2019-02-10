package com.voucher.api.v1.infrastructure.service.client;

import com.voucher.api.v1.core.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetClientByIdServiceIT {

    @Autowired
    private GetClientByIdService getClientByIdService;

    @Test(expected = HttpClientErrorException.NotFound.class)
    public void givenClientIdEndpoint_WhenInvalidClientId_ShouldThrowException() {
        getClientByIdService.search("G7cwfHokOtDorjqFMuI3tA234");
    }

    @Test
    public void givenClientIdEndpoint_WhenValidClientId_ShouldReturnExpectedClientEmail() {
        String expectedEmail = "0.111085034143845@example.com";

        Client client = getClientByIdService.search("G7cwfHokOtDorjqFMuI3tA");

        assert expectedEmail.equals(client.getEmail());
    }
}
