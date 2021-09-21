package com.digicore.digicore_banking_web_application.service.AccountDaoService;

import com.digicore.digicore_banking_web_application.model.TransactionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class AccountHistoryDaoImpl  implements Dao<TransactionDetails, Map>{
    @Override
    public TransactionDetails save(TransactionDetails transactionDetails) {
        return null;
    }
}
