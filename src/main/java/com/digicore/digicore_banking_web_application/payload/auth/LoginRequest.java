package com.digicore.digicore_banking_web_application.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    @NotBlank
    private String accountNumber;

    @NotBlank
    private String accountPassword;
}
