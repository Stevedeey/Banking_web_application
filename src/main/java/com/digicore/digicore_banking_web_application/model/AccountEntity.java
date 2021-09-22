package com.digicore.digicore_banking_web_application.model;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {

    private String accountName;

    private String accountNumber;

    private Double balance;

    private String password;
}
