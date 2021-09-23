package com.digicore.digicore_banking_web_application.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WithdrawalRequest {

    @NotNull(message = "Account number cannot be empty")
    private String accountNumber;

    @NotNull(message = "Password cannot be empty")
    private String accountPassword;

    @NotNull(message = "Amount cannot be empty")
    private Double withdrawnAmount;

    @NotNull(message = "Narration cannot be empty")
    private String narration;

}
