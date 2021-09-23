package com.digicore.digicore_banking_web_application.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepositRequest {

    @NotNull(message = "Account number cannot be empty")
    private String accountNumber;

    @NotNull(message = "Amount cannot be empty")
    private Double amount;

    @NotNull(message = "Narration  cannot be empty")
    private String narration;
}
