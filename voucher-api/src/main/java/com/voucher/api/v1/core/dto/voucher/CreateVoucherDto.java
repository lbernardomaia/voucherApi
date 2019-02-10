package com.voucher.api.v1.core.dto.voucher;

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
