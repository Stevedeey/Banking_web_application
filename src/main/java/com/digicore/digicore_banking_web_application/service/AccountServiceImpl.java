package com.digicore.digicore_banking_web_application.service;

import com.digicore.digicore_banking_web_application.exception.ApiRequestException;
import com.digicore.digicore_banking_web_application.exception.ApiRequestUnauthorizedException;
import com.digicore.digicore_banking_web_application.model.AccountEntity;
import com.digicore.digicore_banking_web_application.payload.auth.LoginRequest;
import com.digicore.digicore_banking_web_application.payload.auth.LoginResponse;
import com.digicore.digicore_banking_web_application.payload.requests.CreateAccountRequest;
import com.digicore.digicore_banking_web_application.payload.requests.DepositRequest;
import com.digicore.digicore_banking_web_application.payload.requests.WithdrawalRequest;
import com.digicore.digicore_banking_web_application.payload.response.*;
import com.digicore.digicore_banking_web_application.security.jwt.JwtUtils;
import com.digicore.digicore_banking_web_application.security.security_service.UserDetailsImpl;
import com.digicore.digicore_banking_web_application.service.AccountDaoService.AccountHistoryDaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService{

    private final Double maximumDeposit = 1000000.0;
    private final Double minimumDeposit = 1.0;
    private final Double minimumBalance = 500.0;

    private HashMap<String, AccountEntity> accountMap = new HashMap<>();
    List<String> nameList = new ArrayList<>();

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private AccountHistoryDaoImpl accountHistoryDao;


    @Autowired
    public AccountServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                              AccountHistoryDaoImpl accountHistoryDao) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

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
        Authentication authentication = null;
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

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

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
