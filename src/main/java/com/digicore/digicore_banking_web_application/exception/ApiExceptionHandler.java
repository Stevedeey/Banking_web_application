package com.digicore.digicore_banking_web_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
@ResponseStatus
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(e.getMessage(),
                false, badRequest, ZonedDateTime.now(ZoneId.of("Z")));


        //  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiException);
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ApiResourceNotFoundException.class})
    public ResponseEntity<Object> handleApiResourceNotFoundException(ApiResourceNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(e.getMessage(),
                false, notFound, ZonedDateTime.now(ZoneId.of("Z")));


        return new ResponseEntity<>(apiException, notFound);
    }
}
