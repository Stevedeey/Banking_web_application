package com.digicore.digicore_banking_web_application.service;

import com.digicore.digicore_banking_web_application.exception.ApiRequestUnauthorizedException;
import com.digicore.digicore_banking_web_application.model.AccountEntity;
import com.digicore.digicore_banking_web_application.model.TransactionDetails;
import com.digicore.digicore_banking_web_application.payload.auth.LoginRequest;
import com.digicore.digicore_banking_web_application.payload.auth.LoginResponse;
import com.digicore.digicore_banking_web_application.payload.requests.CreateAccountRequest;
import com.digicore.digicore_banking_web_application.payload.requests.DepositRequest;
import com.digicore.digicore_banking_web_application.payload.requests.WithdrawalRequest;
import com.digicore.digicore_banking_web_application.payload.response.*;
import com.digicore.digicore_banking_web_application.security.jwt.JwtUtils;
import com.digicore.digicore_banking_web_application.security.security_service.UserDetailsImpl;
import com.digicore.digicore_banking_web_application.service.AccountDaoService.AccountHistoryDaoImpl;

import com.digicore.digicore_banking_web_application.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService{

    private final Double maximumDeposit = 1000000.0;
    private final Double minimumDeposit = 1.0;
    private final Double minimumBalance = 500.0;

    private HashMap<String, AccountEntity> accountMap = new HashMap<>();
    List<String> nameList = new ArrayList<>();

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private  AccountHistoryDaoImpl accountHistoryDao;



    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public AccountServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                              AccountHistoryDaoImpl accountHistoryDao,
                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Map<String, AccountEntity> getAccountMap() {
        return null;
    }

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {

        String accountNumber = Util.generateAccountID();

        Date currentDate = Util.generateCurrentDate();

        CreateAccountResponse createAccountResponse;

        if (nameList.contains(createAccountRequest.getAccountName())) { //checking if there is a user with this name
            createAccountResponse = new CreateAccountResponse();
            createAccountResponse.setResponseCode(400);
            createAccountResponse.setSuccess(false);
            createAccountResponse.setMessage("User with this name already exist");
            return createAccountResponse;
        }

        if(accountMap.containsKey(accountNumber)){
            createAccount(createAccountRequest);
        }

        //Enforce minimum balance
        if (createAccountRequest.getInitialDeposit() < minimumBalance) {

            createAccountResponse = new CreateAccountResponse();
            createAccountResponse.setResponseCode(400);
            createAccountResponse.setSuccess(false);
            createAccountResponse.setMessage("Your initial deposit cannot be less than 500 ");
            return createAccountResponse;

        }

        AccountEntity account = new AccountEntity();
        Double initialDeposit = createAccountRequest.getInitialDeposit();
        account.setAccountName(createAccountRequest.getAccountName());
        account.setAccountNumber(accountNumber);
        account.setBalance(initialDeposit);
        account.setPassword(bCryptPasswordEncoder.encode(createAccountRequest.getAccountPassword()));

        TransactionDetails transactionDetail = new TransactionDetails();

        transactionDetail.setTransactionDate(currentDate);
        transactionDetail.setTransactionType("Deposit");
        transactionDetail.setAccountNumber(accountNumber);
        transactionDetail.setAccountBalance(initialDeposit);
        transactionDetail.setAmount(initialDeposit);
        transactionDetail.setNarration("Initial Deposit");

        if (accountMap == null) accountMap = new HashMap<>();

        accountMap.put(accountNumber, account);
        nameList.add(createAccountRequest.getAccountName()); // Using this to keep track of account names

        createAccountResponse = new CreateAccountResponse();
        createAccountResponse.setMessage("Account successfully created" + "Your new account number is " + accountNumber);
        createAccountResponse.setSuccess(true);
        createAccountResponse.setResponseCode(200);


        return createAccountResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication;
        try {

            authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(
                            loginRequest.getAccountNumber(),
                            loginRequest.getAccountPassword()));

        }catch (BadCredentialsException exe){

            throw new ApiRequestUnauthorizedException("Wrong Credentials!!!");

        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtString = jwtUtils.generateJwtToken(authentication);


        return new LoginResponse(true, jwtString);
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
