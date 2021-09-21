package com.digicore.digicore_banking_web_application.payload.requests;

import com.digicore.digicore_banking_web_application.payload.response.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoRequest {

    private int responseCode;

    private boolean success;

    private String message;

    private AccountResponse accountResponse;
}
