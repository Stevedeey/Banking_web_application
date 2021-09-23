package com.digicore.digicore_banking_web_application.service;

import com.digicore.digicore_banking_web_application.model.AccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

   @Autowired
    private AccountService accountService;

    private HashMap<String, AccountEntity> accountMap = new HashMap<>();

    @BeforeEach
    void setUp() {

        AccountEntity accountEntity =
                AccountEntity.builder()
                .accountNumber("1234567890")
                .accountName("Olaleye Oluwatosin")
                .balance(900.00)
                        .build();

        accountMap.put(accountEntity.getAccountNumber(), accountEntity);


    }



    @Test
    public void testCaseForCreateAccount(){


    }

    @Test
    public void testCaseForWhenAccountNumberIsSuppliedToFetchAccountInfo(){

        String accountNumber = "1234567890";

        AccountEntity accountEntity = accountMap.get(accountNumber);

        assertEquals(accountNumber, accountEntity.getAccountNumber());

    }
}