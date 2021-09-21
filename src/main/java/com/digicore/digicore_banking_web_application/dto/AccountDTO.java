package com.digicore.digicore_banking_web_application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String accountName;

    private String accountNumber;

    private Double balance;

}
