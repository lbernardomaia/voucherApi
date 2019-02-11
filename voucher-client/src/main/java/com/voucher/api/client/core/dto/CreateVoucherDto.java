package com.voucher.api.client.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVoucherDto {
    private String clientId;
    private Double balance;
}
