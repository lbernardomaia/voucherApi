package com.voucher.api.v1.core.dto.client;

import lombok.Data;

@Data
public class ClientDto {
    private String clientId;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}
