package com.digicore.digicore_banking_web_application.controller;

import com.digicore.digicore_banking_web_application.Transaction.TransactionHistory;
import com.digicore.digicore_banking_web_application.payload.requests.CreateAccountRequest;
import com.digicore.digicore_banking_web_application.payload.requests.DepositRequest;
import com.digicore.digicore_banking_web_application.payload.requests.WithdrawalRequest;
import com.digicore.digicore_banking_web_application.payload.response.AccountInfoResponse;
import com.digicore.digicore_banking_web_application.payload.response.CreateAccountResponse;
import com.digicore.digicore_banking_web_application.payload.response.DepositResponse;
import com.digicore.digicore_banking_web_application.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {


}
