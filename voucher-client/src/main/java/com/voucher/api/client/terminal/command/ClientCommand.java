package com.voucher.api.client.terminal.command;

import com.voucher.api.client.core.dto.SearchClientDto;
import com.voucher.api.client.core.service.client.ClientService;
import com.voucher.api.client.terminal.transformer.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.util.function.Supplier;

@ShellComponent
@ShellCommandGroup("Client API Commands")
public class ClientCommand {

    private static Logger LOG = LoggerFactory.getLogger(ClientCommand.class);

    private ClientService clientService;
    private Transformer transformer;

    @Autowired
    public ClientCommand(ClientService clientService, Transformer transformer) {
        this.clientService = clientService;
        this.transformer = transformer;
    }

    @ShellMethod(value = "Search a client by ID. E.g. client G7cwfHokOtDorjqFMuI3tA", key = "client")
    public String getClient(final String id) throws IOException {
        LOG.info("getClient ID {}", id);

        return transformer.transform(() -> clientService.getClientById(id));

    }

    @ShellMethod(value = "Search a client by email, firstName, last-name, phone." +
            " E.g. searchClient -first-name BAILEY", key = "searchClient", prefix="-")
    public String searchClient(@ShellOption(defaultValue = "") final String email,
                                 @ShellOption(defaultValue = "") final String firstName,
                                 @ShellOption(defaultValue = "") final String lastName,
                                 @ShellOption(defaultValue = "") final String phone) throws IOException {
        LOG.info("Search Client by(email {}, firstName {}, lastName {}, phone {})", email, firstName, lastName, phone);

        Supplier<Object> search = () -> clientService.searchBy(new SearchClientDto(email, phone, firstName, lastName));

        return transformer.transform(search);
    }
}
