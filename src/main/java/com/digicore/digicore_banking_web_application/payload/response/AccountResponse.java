package com.digicore.digicore_banking_web_application.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private String accountName;

    private String accountNumber;

    private Double balance;
}
