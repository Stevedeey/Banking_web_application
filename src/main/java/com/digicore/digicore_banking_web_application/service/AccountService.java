package com.digicore.digicore_banking_web_application.service;

import com.digicore.digicore_banking_web_application.model.AccountEntity;
import com.digicore.digicore_banking_web_application.payload.auth.LoginRequest;
import com.digicore.digicore_banking_web_application.payload.auth.LoginResponse;
import com.digicore.digicore_banking_web_application.payload.requests.CreateAccountRequest;
import com.digicore.digicore_banking_web_application.payload.requests.DepositRequest;
import com.digicore.digicore_banking_web_application.payload.requests.WithdrawalRequest;
import com.digicore.digicore_banking_web_application.payload.response.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Map;

public interface AccountService {

    Map<String, AccountEntity> getAccountMap();
    CreateAccountResponse createAccount (CreateAccountRequest createAccountRequest);
    LoginResponse login(LoginRequest loginRequest);
    WithdrawalResponse withdraw (WithdrawalRequest request);
    ResponseEntity<DepositResponse> deposit(DepositRequest depositRequest);
    ResponseEntity<AccountInfoResponse> getAccountInfo (String accountNumber);
    ResponseEntity<List<AccountHistoryResponse>> getTransactionHistory(String accountNumber);
}
