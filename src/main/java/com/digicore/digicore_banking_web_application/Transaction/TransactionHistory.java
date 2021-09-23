package com.digicore.digicore_banking_web_application.Transaction;

import com.digicore.digicore_banking_web_application.payload.response.TransactionHistoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionHistory {

    private List<TransactionHistoryResponse> transactionHistoryList;

    public List<TransactionHistoryResponse> getTransactionHistoryList() {
        return transactionHistoryList;
    }

    public void setTransactionHistoryList(List<TransactionHistoryResponse> transactionHistoryList) {
        this.transactionHistoryList = transactionHistoryList;
    }
}
