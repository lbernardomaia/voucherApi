package com.voucher.api.v1.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voucher {
    String clientId;
    String voucherId;
    String serialNumber;
    LocalDateTime issueDate;
    LocalDateTime expiryDate;
    String creatingBranchId;
    Double originalBalance;
    Double remainingBalance;
}
