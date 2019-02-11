package com.voucher.api.client.core.client.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voucher.api.client.client.transformer.Transformer;
import com.voucher.api.client.client.transformer.TransformerJson;
import com.voucher.api.client.core.dto.ClientDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransformerJsonTest {

    private Transformer transformer;

    @Before
    public void setUp() {
        transformer = new TransformerJson(new ObjectMapper());
    }

    @Test
    public void givenClientDto_WhenConvertedToJson_ThenJsonIsReturned() throws IOException {
        String expectedResult = "{\n" +
                "  \"clientId\" : \"1\",\n" +
                "  \"email\" : \"2\",\n" +
                "  \"phone\" : \"3\",\n" +
                "  \"firstName\" : \"4\",\n" +
                "  \"lastName\" : \"5\"\n" +
                "}";

        final String result = transformer.transform(() -> new ClientDto("1","2","3","4","5"));

        assert expectedResult.equals(result);
    }
}
