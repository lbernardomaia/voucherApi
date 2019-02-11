package com.voucher.api.client.command;

import com.voucher.api.client.core.service.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.function.Supplier;

@ShellComponent
@ShellCommandGroup("Voucher API Commands")
public class VoucherCommand {

    private static Logger LOG = LoggerFactory.getLogger(VoucherCommand.class);

    private VoucherService voucherService;

    @Autowired
    public VoucherCommand(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @ShellMethod(value = "Search Voucher E.g. searchVoucher WwEaIb0m4bhJphVtm2VgIw | searchVoucher WwEaIb0m4bhJphVtm2VgIw 10026", key = "searchVoucher")
    public String searchVoucher(@ShellOption(defaultValue = "") String clientId,
                                @ShellOption(defaultValue = "") String serialNumber) {
        LOG.info("Search Voucher {},{}", clientId, serialNumber);

        Supplier<String> search = () -> voucherService.search(clientId, serialNumber);

        return executeSearch(search);
    }

    @ShellMethod(value = "Create a Voucher E.g. createVoucher WwEaIb0m4bhJphVtm2VgIw 50.0", key = "createVoucher")
    public String create(String clientId, Double balance) {
        LOG.info("Create Voucher {},{}", clientId, balance);

        Supplier<String> create = () -> voucherService.create(clientId, balance);

        return executeSearch(create);
    }

    private String executeSearch(Supplier<String> action){
        try{
            return action.get();
        }catch (Exception e){
            return "Unable to process the operation. Error: " + e.getMessage();
        }
    }
}
