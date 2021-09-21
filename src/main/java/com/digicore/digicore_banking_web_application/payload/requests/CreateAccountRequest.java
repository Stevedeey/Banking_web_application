package com.digicore.digicore_banking_web_application.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CreateAccountRequest {

    private String accountName;

    private String accountPassword;

    private Double initialDeposit;

    public CreateAccountRequest() {
        initialDeposit = 0.0;
    }
}
