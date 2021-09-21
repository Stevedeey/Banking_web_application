package com.digicore.digicore_banking_web_application.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WithdrawalRequest {

    String accountNumber;

    String accountPassword;

    Double withdrawnAmount;

    String narration;

}
