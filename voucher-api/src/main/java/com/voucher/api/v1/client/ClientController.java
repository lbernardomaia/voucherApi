package com.voucher.api.v1.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    private static Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @GetMapping("/client")
    public List<String> getCLient(){
        LOG.info("client api");
        return Arrays.asList("John","David","Declan");
    }
}
