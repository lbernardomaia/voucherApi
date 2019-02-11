package com.voucher.api.client.terminal.command;

import com.voucher.api.client.terminal.transformer.Transformer;
import com.voucher.api.client.core.service.VoucherService;
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
@ShellCommandGroup("Voucher API Commands")
public class VoucherCommand {

    private static Logger LOG = LoggerFactory.getLogger(VoucherCommand.class);

    private VoucherService voucherService;
    private Transformer transformer;

    @Autowired
    public VoucherCommand(VoucherService voucherService, Transformer transformer) {
        this.voucherService = voucherService;
        this.transformer = transformer;
    }

    @ShellMethod(value = "Search Voucher E.g. searchVoucher WwEaIb0m4bhJphVtm2VgIw | searchVoucher WwEaIb0m4bhJphVtm2VgIw 10026", key = "searchVoucher")
    public String searchVoucher(@ShellOption(defaultValue = "") String clientId,
                                @ShellOption(defaultValue = "") String serialNumber) throws IOException {
        LOG.info("Search Voucher {},{}", clientId, serialNumber);

        Supplier<Object> search = () -> voucherService.search(clientId, serialNumber);

        return transformer.transform(search);
    }

    @ShellMethod(value = "Create a Voucher E.g. createVoucher WwEaIb0m4bhJphVtm2VgIw 50.0", key = "createVoucher")
    public String create(String clientId, Double balance) throws IOException {
        LOG.info("Create Voucher {},{}", clientId, balance);

        Supplier<Object> create = () -> voucherService.create(clientId, balance);

        return transformer.transform(create);
    }
}
