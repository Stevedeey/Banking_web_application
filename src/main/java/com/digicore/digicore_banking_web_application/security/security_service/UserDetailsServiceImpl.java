package com.digicore.digicore_banking_web_application.security.security_service;

import com.digicore.digicore_banking_web_application.exception.ApiResourceNotFoundException;
import com.digicore.digicore_banking_web_application.model.AccountEntity;
import com.digicore.digicore_banking_web_application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {

        AccountEntity account = this.accountService.getAccountMap().get(accountNumber);

        if (account == null) {
            throw new ApiResourceNotFoundException( accountNumber + " does not exist in our database");
        }

        return UserDetailsImpl.build(account);
    }
}
