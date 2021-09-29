package com.digicore.digicore_banking_web_application.service;

import com.digicore.digicore_banking_web_application.Transaction.TransactionHistory;
import com.digicore.digicore_banking_web_application.exception.ApiBadRequestException;
import com.digicore.digicore_banking_web_application.exception.ApiRequestUnauthorizedException;
import com.digicore.digicore_banking_web_application.exception.ApiResourceNotFoundException;
import com.digicore.digicore_banking_web_application.model.AccountEntity;
import com.digicore.digicore_banking_web_application.Transaction.ETransactionType;
import com.digicore.digicore_banking_web_application.Transaction.TransactionDetail;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
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
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final Double maximumDeposit = 1000000.0;
    private final Double minimumDeposit = 1.0;
    private final Double minimumBalance = 500.0;

    private HashMap<String, AccountEntity> accountMap = new HashMap<>();
    List<String> nameList = new ArrayList<>();

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private AccountHistoryDaoImpl accountHistoryDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setAccountHistoryDao(AccountHistoryDaoImpl accountHistoryDao) {
        this.accountHistoryDao = accountHistoryDao;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public Map<String, AccountEntity> getAccountMap() {
        return accountMap;
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

        if (accountMap.containsKey(accountNumber)) {
            createAccount(createAccountRequest);
        }

        //Enforce minimum balance
        if (createAccountRequest.getInitialDeposit() < minimumBalance) {

            createAccountResponse = new CreateAccountResponse();
            createAccountResponse.setResponseCode(400);
            createAccountResponse.setSuccess(false);
            createAccountResponse.setMessage("Your initial deposit cannot be less than 500");
            return createAccountResponse;

        }

        AccountEntity account = new AccountEntity();
        Double initialDeposit = createAccountRequest.getInitialDeposit();
        account.setAccountName(createAccountRequest.getAccountName());
        account.setAccountNumber(accountNumber);
        account.setBalance(initialDeposit);
        account.setPassword(bCryptPasswordEncoder.encode(createAccountRequest.getAccountPassword()));

        TransactionDetail transactionDetail = new TransactionDetail();

        transactionDetail.setTransactionDate(currentDate);
        transactionDetail.setTransactionType("Deposit");
        transactionDetail.setAccountNumber(accountNumber);
        transactionDetail.setAccountBalance(initialDeposit);
        transactionDetail.setAmount(initialDeposit);
        transactionDetail.setNarration("Initial Deposit");

        var history = accountHistoryDao.save(transactionDetail);

        if (accountMap == null) accountMap = new HashMap<>();

        accountMap.put(accountNumber, account);
        nameList.add(createAccountRequest.getAccountName()); // Using this to keep track of account names

        createAccountResponse = new CreateAccountResponse();
        createAccountResponse.setMessage("Account successfully created," + " Your new account number is " + accountNumber);
        createAccountResponse.setSuccess(true);
        createAccountResponse.setResponseCode(200);

        System.out.println("This is the state of account map " + accountMap);


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

        } catch (BadCredentialsException exe) {

            throw new ApiRequestUnauthorizedException("Wrong Credentials!!!");

        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtString = jwtUtils.generateJwtToken(authentication);


        return new LoginResponse(true, jwtString);
    }




    @Override
    public WithdrawalResponse withdraw(WithdrawalRequest withdrawalRequest) {

        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String accountNumber = principal.getUsername();

        AccountEntity userAccount = getAccountMap().get(accountNumber);

        WithdrawalResponse response;

        if (!userAccount.getAccountNumber().equals(withdrawalRequest.getAccountNumber())) { //indicate that the user has not supplied his account or does not match

            response = new WithdrawalResponse();
            response.setResponseCode(400);
            response.setSuccess(false);
            response.setMessage("Account does not exist! ");

            return response;
        }

        boolean match = bCryptPasswordEncoder.matches(withdrawalRequest.getAccountPassword(), principal.getPassword());

        if (!match) {
            response = new WithdrawalResponse();
            response.setResponseCode(400);
            response.setSuccess(false);
            response.setMessage("You are not authorized to withdraw from this account!");
            return response;

        }

        if (userAccount.getBalance() - withdrawalRequest.getWithdrawnAmount() < minimumBalance) {
            response = new WithdrawalResponse();

            response.setResponseCode(400);
            response.setSuccess(false);
            response.setMessage("You  must have a minimum of #500 in your account ");

            return response;
        }

        //withdrawing....

        Double newBalance = userAccount.getBalance() - withdrawalRequest.getWithdrawnAmount();
        userAccount.setBalance(newBalance);

        //set transactionDetail
        Date currentDate = Util.generateCurrentDate();

        TransactionDetail transactionDetail = new TransactionDetail();

        transactionDetail.setAccountNumber(withdrawalRequest.getAccountNumber());
        transactionDetail.setAmount(withdrawalRequest.getWithdrawnAmount());
        transactionDetail.setAccountBalance(newBalance);
        transactionDetail.setTransactionDate(currentDate);
        transactionDetail.setTransactionType(ETransactionType.WITHDRAW.toString());
        transactionDetail.setNarration(withdrawalRequest.getNarration());

        accountHistoryDao.save(transactionDetail);

        response = new WithdrawalResponse();

        response.setMessage("You withdrew the amount of "
                +withdrawalRequest.getWithdrawnAmount()
                + "successfully, Your new balance is: "
                + userAccount.getBalance());
        response.setSuccess(true);
        response.setResponseCode(200);


        log.info("Balance {} ", userAccount.getBalance());

        return response;
    }

    @Override
    public ResponseEntity<DepositResponse> deposit(DepositRequest depositRequest) {
        DepositResponse res = new DepositResponse();

        //getting account by accountNumber
        AccountEntity userAccount = getAccountMap().get(depositRequest.getAccountNumber());


        if (depositRequest.getAmount() >= maximumDeposit || depositRequest.getAmount() <= minimumDeposit)
            throw new ApiBadRequestException("Error depositing funds");

        if (userAccount != null) {

            //do Deposit
            Double newBalance = userAccount.getBalance() + depositRequest.getAmount();
            userAccount.setBalance(newBalance);

            var transactionDetail = getTransactionDetail(new TransactionDetail(),
                    depositRequest, newBalance);
            accountHistoryDao.save(transactionDetail);
            log.info(":::Got into the deposit and save transaction history");

        } else {
            throw new ApiResourceNotFoundException("Account does not exist!!!");
        }


        res.setMessage("Successfully deposited a sum of "+ depositRequest.getAmount() +" Your new balance is " +userAccount.getBalance());
        res.setSuccess(true);
        res.setResponseCode(200);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountInfoResponse> getAccountInfo(String accountNumber) {

        AccountInfoResponse accountInfoResponse = new AccountInfoResponse();
        AccountResponse accountResponse = new AccountResponse();

        AccountEntity account = accountMap.get(accountNumber);

        if (account != null) {

            accountResponse.setAccountName(account.getAccountName());
            accountResponse.setAccountNumber(account.getAccountNumber());
            accountResponse.setBalance(account.getBalance());

            accountInfoResponse.setAccountResponse(accountResponse);
        } else {
            throw new ApiResourceNotFoundException("No user with this username found in our database!!");
        }

        accountInfoResponse.setMessage("Account info fetched successfully!!");
        accountInfoResponse.setResponseCode(200);
        accountInfoResponse.setSuccess(true);

        return new ResponseEntity<>(accountInfoResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TransactionHistory> getTransactionHistory(String accountNumber) {

        var transactionHistoryList = accountHistoryDao.getAccountHistory(accountNumber);
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionHistoryList(transactionHistoryList);

        return new ResponseEntity<>(transactionHistory, HttpStatus.OK);
    }

    //::: HELPER METHODS::::::://///

    private static String getLoggedInUserName() {

        return SecurityContextHolder.getContext().getAuthentication().getName(); //getLogged in user
    }

    private static TransactionDetail getTransactionDetail(TransactionDetail transactionDetail, DepositRequest depositRequest, Double newBalance) {

        Date currentDate = Util.generateCurrentDate();

        transactionDetail.setAccountNumber(depositRequest.getAccountNumber());
        transactionDetail.setAmount(depositRequest.getAmount());
        transactionDetail.setAccountBalance(newBalance);
        transactionDetail.setTransactionDate(currentDate);
        transactionDetail.setTransactionType(ETransactionType.DEPOSIT.toString());
        transactionDetail.setNarration(depositRequest.getNarration());

        return transactionDetail;
    }

}
