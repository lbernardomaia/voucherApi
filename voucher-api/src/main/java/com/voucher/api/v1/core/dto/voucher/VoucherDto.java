package com.voucher.api.v1.core.dto.voucher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherDto {
    private String clientId;
    private String serialNumber;
    private Double originalBalance;
}
