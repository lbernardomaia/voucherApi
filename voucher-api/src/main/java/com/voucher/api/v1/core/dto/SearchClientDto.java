package com.voucher.api.v1.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchClientDto {
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}
