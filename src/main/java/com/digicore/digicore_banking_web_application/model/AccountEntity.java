package com.digicore.digicore_banking_web_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    private String accountName;

    private String accountNumber;

    private Double balance;

    private String password;
}
