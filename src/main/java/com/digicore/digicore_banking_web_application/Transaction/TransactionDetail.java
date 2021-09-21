package com.digicore.digicore_banking_web_application.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetail {

    private String accountNumber;

    private Date transactionDate;

    private String transactionType;

    private String narration;

    private Double amount;

    private Double accountBalance;
}
