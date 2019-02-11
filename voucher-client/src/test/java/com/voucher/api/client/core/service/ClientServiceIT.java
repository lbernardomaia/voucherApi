package com.voucher.api.client.core.service;

import com.voucher.api.client.core.dto.ClientDto;
import com.voucher.api.client.core.dto.SearchClientDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        final String clientId = "u-55JsXUtlzGMs2OuYF0NA";

        final ClientDto clientById = clientService.getClientById(clientId);

        assert clientById.getClientId().equals(clientId);
    }

    @Test
    public void givenSearchClientBy_WhenHasValidParameter_ThenReturnClient() {
        final List<ClientDto> bailey = clientService.searchBy(new SearchClientDto("", "", "BAILEY", ""));

        assert !bailey.isEmpty();
    }
}
