package com.voucher.api.v1.infrastructure.service.client;

import com.voucher.api.v1.core.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchByClientServiceIT {

    @Autowired
    private SearchByClientService searchByClient;

    @Test
    public void givenClientEndpoint_WhenClientIsNull_ShouldReturnEmpty() {
        Collection<Client> clients = searchByClient.search(null);

        assert clients.isEmpty();
    }

    @Test
    public void givenClientEndpoint_WhenClientIsEmpty_ShouldReturnSomething() {
        Collection<Client> clients = searchByClient.search(new Client());

        assert !clients.isEmpty();
    }

    @Test
    public void givenClientEndpoint_WhenClientHasFirstName_ShouldReturnSomething() {
        Client client = Client.builder().firstName("Test").build();

        Collection<Client> clients = searchByClient.search(client);

        assert !clients.isEmpty();
    }

    @Test
    public void givenClientEndpoint_WhenClientHasLastName_ShouldReturnSomething() {
        Client client = Client.builder().lastName("Test").build();

        Collection<Client> clients = searchByClient.search(client);

        assert !clients.isEmpty();
    }

    @Test
    public void givenClientEndpoint_WhenClientHasPhone_ShouldReturnSomething() {
        Client client = Client.builder().phone("04711302676581").build();

        Collection<Client> clients = searchByClient.search(client);

        assert !clients.isEmpty();
    }

    @Test
    public void givenClientEndpoint_WhenClientHasEmail_ShouldReturnSomething() {
        Client client = Client.builder().email("0.108903190757477@example.com").build();

        Collection<Client> clients = searchByClient.search(client);

        assert !clients.isEmpty();
    }

    @Test
    public void givenClientEndpoint_WhenClientHasNonRegisteredEmail_ShouldReturnEmpty() {
        Client client = Client.builder().email("123098786@example.com").build();

        Collection<Client> clients = searchByClient.search(client);

        assert clients.isEmpty();
    }
}
