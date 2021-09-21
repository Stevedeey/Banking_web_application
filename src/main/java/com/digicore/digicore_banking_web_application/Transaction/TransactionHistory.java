package com.digicore.digicore_banking_web_application.Transaction;

import com.digicore.digicore_banking_web_application.payload.response.TransactionHistoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionHistory {

    private List<TransactionHistoryResponse> transactionHistoryResponseList;

    public List<TransactionHistoryResponse> getTransactionHistoryResponseList() {
        return transactionHistoryResponseList;
    }

    public void setTransactionHistoryResponseList(List<TransactionHistoryResponse> transactionHistoryResponseList) {
        this.transactionHistoryResponseList = transactionHistoryResponseList;
    }
}
