package com.digicore.digicore_banking_web_application.controller;

import com.digicore.digicore_banking_web_application.payload.auth.LoginRequest;
import com.digicore.digicore_banking_web_application.payload.auth.LoginResponse;
import com.digicore.digicore_banking_web_application.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final AccountService accountService;

    public AuthController(AuthenticationManager authenticationManager, AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> doLogin(@RequestBody LoginRequest loginRequest){

        var loginUser = accountService.login(loginRequest);

        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }
}
