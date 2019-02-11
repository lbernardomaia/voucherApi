package com.voucher.api.client.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private String clientId;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}
