package com.digicore.digicore_banking_web_application.service.AccountDaoService;

import com.digicore.digicore_banking_web_application.Transaction.TransactionDetail;
import com.digicore.digicore_banking_web_application.payload.response.TransactionHistoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AccountHistoryDaoImpl  implements Dao<TransactionDetail, Map>{

    private Map<String, List<TransactionHistoryResponse>> accountHistoryMap = new HashMap<>();

    @Override
    public TransactionDetail save(TransactionDetail transactionDetail) {


        TransactionHistoryResponse transactionHistoryResponse = new TransactionHistoryResponse();


        transactionHistoryResponse.setAccountBalance(transactionDetail.getAccountBalance());
        transactionHistoryResponse.setAmount(transactionDetail.getAmount());
        transactionHistoryResponse.setTransactionDate(transactionDetail.getTransactionDate());
        transactionHistoryResponse.setNarration(transactionDetail.getNarration());
        transactionHistoryResponse.setTransactionType(transactionDetail.getTransactionType());


        if (accountHistoryMap.containsKey(transactionDetail.getAccountNumber())) {
            List<TransactionHistoryResponse> infoList = accountHistoryMap.get(transactionDetail.getAccountNumber());
            infoList.add(transactionHistoryResponse);

            accountHistoryMap.put(transactionDetail.getAccountNumber(), infoList);

        } else {
            List<TransactionHistoryResponse> accountHistoryList =  new ArrayList<>();
            accountHistoryList.add(transactionHistoryResponse);

            accountHistoryMap.put(transactionDetail.getAccountNumber(), accountHistoryList);
        }


        return transactionDetail;
    }


    public List<TransactionHistoryResponse> getAccountHistory (String accountNumber){
        log.info(":::Account History Map{}",accountHistoryMap.get(accountNumber));
        return accountHistoryMap.get(accountNumber) != null ? accountHistoryMap.get(accountNumber) : new ArrayList<>();
    }
}
