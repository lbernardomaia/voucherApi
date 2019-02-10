package com.voucher.api.client.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    private String clientId;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}
