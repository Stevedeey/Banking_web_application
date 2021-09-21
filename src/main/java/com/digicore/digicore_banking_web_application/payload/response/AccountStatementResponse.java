package com.digicore.digicore_banking_web_application.payload.response;

import com.digicore.digicore_banking_web_application.model.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountStatementResponse {

    private Date transactionDate;

    private String transactionType;

    private String narration;

    private  Double amount;

    private Double accountBalance;

    private AccountEntity account;


}
