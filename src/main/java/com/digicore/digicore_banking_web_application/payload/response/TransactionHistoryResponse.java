package com.digicore.digicore_banking_web_application.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryResponse {

    private Date transactionDate;

    private String transactionType;

    private String narration;

    private Double amount;

    private Double accountBalance;
}
