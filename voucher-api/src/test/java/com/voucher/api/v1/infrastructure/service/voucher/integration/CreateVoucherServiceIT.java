package com.voucher.api.v1.infrastructure.service.voucher.integration;

import com.voucher.api.v1.AppConfigTest;
import com.voucher.api.v1.core.model.Voucher;
import com.voucher.api.v1.core.service.voucher.CreateVoucherService;
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

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest({ CreateVoucherService.class })
@ContextConfiguration(classes = {AppConfigTest.class})
public class CreateVoucherServiceIT {

    @Autowired
    private CreateVoucherService createVoucherService;

    @Autowired
    private MockRestServiceServer server;

    @Value("${uri}")
    private String uri;

    @Value("${voucher.endpoint}")
    private String endpoint;

    @Value("${businessId}")
    private String businessId;

    @Value("${branchId}")
    private String branchId;

    @Autowired
    private ResourceLoader resourceLoader;

    @Before
    public void setUp() throws Exception {
        final URI uri = resourceLoader.getResource("/voucher/voucherCreated.json").getURI();

        byte[] vouchersPage1Encoded = Files.readAllBytes(Paths.get(uri));

        String voucherEndpoint = UriComponentsBuilder.fromHttpUrl(this.uri).pathSegment(businessId, endpoint).toUriString();

        this.server.expect(requestTo(voucherEndpoint))
                .andRespond(withSuccess(new String(vouchersPage1Encoded), MediaTypes.HAL_JSON));
    }

    @Test
    public void givenVoucherEndpoint_WhenHttpVerbIsPost_ThenCreateVoucherAndReturnSerialNumber() {
        Voucher voucher = Voucher.builder().clientId("Nir6yiEEinni-2_47QWyUA")
                                           .creatingBranchId(branchId)
                                           .expiryDate(LocalDateTime.now())
                                           .issueDate(LocalDateTime.now())
                                           .originalBalance(123.12d)
                                           .voucherId("ab-123")
                                           .build();

        Voucher result = createVoucherService.create(voucher);

        assert result.getSerialNumber().equals("11294");
    }
}