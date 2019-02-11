package com.voucher.api.v1.core.dto.voucher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVoucherDto {

    @NotBlank(message = "ClientId is required")
    private String clientId;

    @NotNull(message = "Balance is required")
    private Double balance;
}
