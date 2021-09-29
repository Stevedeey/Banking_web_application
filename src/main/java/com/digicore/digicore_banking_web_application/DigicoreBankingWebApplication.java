package com.digicore.digicore_banking_web_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(
        prePostEnabled = true) //To allow for preandPostAuthorize annotations.. This can be in any class that has @Configuration ano
public class DigicoreBankingWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigicoreBankingWebApplication.class, args);
    }

}
