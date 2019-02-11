package com.voucher.api.client.core.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@RunWith(SpringRunner.class)
public class VoucherServiceIT {

    @Autowired
    private VoucherService voucherService;

    @Test
    public void givenVouchert_WhenHasValidClientId_ThenReturnClient() {
        String result = voucherService.search("u-55JsXUtlzGMs2OuYF0NA", "");

        assertNotNull(result);
    }

    @Test
    public void givenNewVoucher_WhenHasValidParameter_ThenReturnVoucherCreated() {
        String result = voucherService.create("WwEaIb0m4bhJphVtm2VgIw", 10d);

        assertNotNull(result);
    }
}