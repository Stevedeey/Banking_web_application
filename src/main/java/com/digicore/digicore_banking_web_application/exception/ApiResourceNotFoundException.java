package com.digicore.digicore_banking_web_application.exception;

public class ApiResourceNotFoundException extends RuntimeException{
    public ApiResourceNotFoundException(String message) {
        super(message);
    }
}
