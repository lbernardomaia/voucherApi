package com.voucher.api.client.core.service;

import com.voucher.api.client.core.dto.SearchClientDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@RunWith(SpringRunner.class)
public class ClientServiceIT {

    @Autowired
    private ClientService clientService;

    @Test
    public void givenGetClient_WhenHasValidCLientId_ThenReturnClient() {
        String result = clientService.getClientById("G7cwfHokOtDorjqFMuI3tAee");

        assertNotNull(result);
    }

    @Test
    public void givenSearchClientBy_WhenHasValidParameter_ThenReturnClient() {
        String result = clientService.searchBy(new SearchClientDto("", "", "BAILEY", ""));

        assertNotNull(result);
    }
}
