package com.voucher.api.client.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voucher {
    String clientId;
    String serialNumber;
    Double balance;
}
