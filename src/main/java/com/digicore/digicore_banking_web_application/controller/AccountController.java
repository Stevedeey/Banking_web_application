package com.digicore.digicore_banking_web_application.controller;

import com.digicore.digicore_banking_web_application.Transaction.TransactionHistory;
import com.digicore.digicore_banking_web_application.payload.requests.CreateAccountRequest;
import com.digicore.digicore_banking_web_application.payload.requests.DepositRequest;
import com.digicore.digicore_banking_web_application.payload.requests.WithdrawalRequest;
import com.digicore.digicore_banking_web_application.payload.response.AccountInfoResponse;
import com.digicore.digicore_banking_web_application.payload.response.CreateAccountResponse;
import com.digicore.digicore_banking_web_application.payload.response.DepositResponse;
import com.digicore.digicore_banking_web_application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }


    @GetMapping(path = "account_info/{accountNumber}")
    public ResponseEntity<AccountInfoResponse> getAccountInfo(@PathVariable String accountNumber) {

        return service.getAccountInfo(accountNumber);
    }

    @GetMapping(path = "account_statement/{accountNumber}")
    public ResponseEntity<TransactionHistory> getTransactionHistory(@PathVariable String accountNumber) {
        return service.getTransactionHistory(accountNumber);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest request) {

        var account = service.createAccount(request);

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping(path = "/withdraw")
    public ResponseEntity<?> doWithdrawal(@RequestBody WithdrawalRequest request) {

        var withdrawal = service.withdraw(request);

        if (withdrawal.isSuccess() == false) return new ResponseEntity<>(withdrawal, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @PostMapping(path = "/deposit")
    public ResponseEntity<DepositResponse> doDeposit(@RequestBody DepositRequest depositRequest) {

        return service.deposit(depositRequest);
    }

}
