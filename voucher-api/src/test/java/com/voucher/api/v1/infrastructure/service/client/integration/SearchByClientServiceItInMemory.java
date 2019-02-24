package com.voucher.api.v1.infrastructure.service.client.integration;

import com.voucher.api.v1.AppConfigTest;
import com.voucher.api.v1.core.model.Client;
import com.voucher.api.v1.infrastructure.service.PagedResourcesSearch;
import com.voucher.api.v1.infrastructure.service.client.SearchByClientQueryParameter;
import com.voucher.api.v1.infrastructure.service.client.SearchByClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest({ SearchByClientService.class, SearchByClientQueryParameter.class, PagedResourcesSearch.class })
@ContextConfiguration(classes = {AppConfigTest.class})
public class SearchByClientServiceItInMemory {

    @Autowired
    private SearchByClientService searchByClient;

    @Autowired
    private MockRestServiceServer server;

    @Value("${uri}")
    private String uri;

    @Value("${client.endpoint}")
    private String endpoint;

    @Value("${businessId}")
    private String businessId;

    @Autowired
    private ResourceLoader resourceLoader;

    @Before
    public void setUp() throws Exception {
        mockEndpointWithResource("/client/clients_page1.json", 0);

        mockEndpointWithResource("/client/clients_page2.json", 1);

        mockEndpoint("", 2);
    }

    private void mockEndpointWithResource(String s, int page) throws IOException {
        final URI vouchersPage1 = resourceLoader.getResource(s).getURI();

        byte[] vouchersPage1Encoded = Files.readAllBytes(Paths.get(vouchersPage1));

        mockEndpoint(new String(vouchersPage1Encoded), page);
    }

    private void mockEndpoint(String content, int page) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uri).pathSegment(businessId, endpoint);
        uriComponentsBuilder.queryParam("page", page);

        this.server.expect(requestTo(uriComponentsBuilder.toUriString()))
                .andRespond(withSuccess(content, MediaTypes.HAL_JSON));
    }

    @Test
    public void givenClientEndpoint_WhenHasTwoPagesToBeReturned_ShouldReturn40Items() {
        final Collection<Client> clients = searchByClient.search(new Client());

        assert clients.size() == 40;
    }
}


