package com.voucher.api.client.command;

import com.voucher.api.client.core.dto.SearchClientDto;
import com.voucher.api.client.core.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.function.Supplier;

@ShellComponent
@ShellCommandGroup("Client API Commands")
public class ClientCommand {

    private static Logger LOG = LoggerFactory.getLogger(ClientCommand.class);

    private ClientService clientService;

    @Autowired
    public ClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @ShellMethod(value = "Search a client by ID. E.g. client G7cwfHokOtDorjqFMuI3tA", key = "client")
    public String getClient(final String id) {
        LOG.info("getClient ID {}", id);

        Supplier<String> getClientById = () -> clientService.getClientById(id);

        return executeSearch(getClientById);
    }

    @ShellMethod(value = "Search a client by email, firstName, last-name, phone." +
            " E.g. searchClient -first-name BAILEY", key = "searchClient", prefix="-")
    public String searchClient(@ShellOption(defaultValue = "") final String email,
                                 @ShellOption(defaultValue = "") final String firstName,
                                 @ShellOption(defaultValue = "") final String lastName,
                                 @ShellOption(defaultValue = "") final String phone) {
        LOG.info("Search Client by(email {}, firstName {}, lastName {}, phone {})", email, firstName, lastName, phone);

        Supplier<String> searchBy = () -> clientService.searchBy(new SearchClientDto(email, phone, firstName, lastName));

        return executeSearch(searchBy);
    }

    private String executeSearch(Supplier<String> action){
        try{
            return action.get();
        }catch (Exception e){
            return "Unable to process the operation. Error: " + e.getMessage();
        }
    }

}
