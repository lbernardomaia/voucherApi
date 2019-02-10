package com.voucher.api.client.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SearchClientDto {
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}
