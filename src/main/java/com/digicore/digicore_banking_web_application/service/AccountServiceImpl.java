package com.digicore.digicore_banking_web_application.service;

import com.digicore.digicore_banking_web_application.model.AccountEntity;
import com.digicore.digicore_banking_web_application.payload.auth.LoginRequest;
import com.digicore.digicore_banking_web_application.payload.auth.LoginResponse;
import com.digicore.digicore_banking_web_application.payload.requests.CreateAccountRequest;
import com.digicore.digicore_banking_web_application.payload.requests.DepositRequest;
import com.digicore.digicore_banking_web_application.payload.requests.WithdrawalRequest;
import com.digicore.digicore_banking_web_application.payload.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService{
    @Override
    public Map<String, AccountEntity> getAccountMap() {
        return null;
    }

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public WithdrawalResponse withdraw(WithdrawalRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<DepositResponse> deposit(DepositRequest depositRequest) {
        return null;
    }

    @Override
    public ResponseEntity<AccountInfoResponse> getAccountInfo(String accountNumber) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccountHistoryResponse>> getTransactionHistory(String accountNumber) {
        return null;
    }
}
