package com.digicore.digicore_banking_web_application.exception;

public class ApiRequestUnauthorizedException extends RuntimeException{

    public ApiRequestUnauthorizedException(String message) {
        super(message);
    }
}
