package com.voucher.api.v1.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("mobile")
    private String phone;

    private String firstName;
    private String lastName;
}
