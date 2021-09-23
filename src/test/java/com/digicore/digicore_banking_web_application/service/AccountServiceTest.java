package com.digicore.digicore_banking_web_application.service;

import com.digicore.digicore_banking_web_application.model.AccountEntity;
import com.digicore.digicore_banking_web_application.payload.requests.CreateAccountRequest;
import com.digicore.digicore_banking_web_application.utility.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
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
    public void testCaseForWhenAccountCreationWasSuccessfull(){

        var accountReq = new CreateAccountRequest();
        accountReq.setAccountName("Daro Joseph");
        accountReq.setInitialDeposit(500.0);
        accountReq.setAccountPassword("1234567890");

        var response = accountService.createAccount(accountReq);

        assertEquals(200, response.getResponseCode());
        assertTrue(response.isSuccess());
        assertNotNull((Object)"Account successfully created, Your new account number is 9941837309", response.getMessage() );

        // Testing for account creation with same account name

        accountReq.setAccountName("Daro Joseph");

        var response2 = accountService.createAccount(accountReq);
        assertEquals(400, response2.getResponseCode());
        assertFalse(response2.isSuccess());
        assertEquals("User with this name already exist", response2.getMessage());

        // Testing for an initial deposit that is less than 500

        accountReq.setInitialDeposit(1.0);
        accountReq.setAccountName("Joseph Daro");

        var response3 = accountService.createAccount(accountReq);
        assertEquals(400, response3.getResponseCode());
        assertFalse(response3.isSuccess());
        assertEquals("Your initial deposit cannot be less than 500", response3.getMessage());


    }

}