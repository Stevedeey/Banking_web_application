package com.digicore.digicore_banking_web_application.utility;

import com.digicore.digicore_banking_web_application.payload.requests.RegistrationRequest;

public class AuthValidator {


        public static String validateRegistration(RegistrationRequest request){
            String validationMessage = "Successful";
            if(!Validator.validateVendorReq(request.getRole(), request.getReferralPhoneNumber())) {
                validationMessage = "Please enter your referral phone number";
            }
            if(!Validator.validateFullName(request.getFullName())){
                validationMessage = "Please enter your full name(Surname and First name and, or middle name)";
            }
            if(!Validator.validatePhoneNumber(request.getPhoneNumber())){
                validationMessage = "Please enter a valid phone number";
            }
            if(request.getReferralPhoneNumber() != null) {
                if(Validator.validatePhoneNumber(request.getReferralPhoneNumber())){
                    validationMessage = "Please enter a valid phone number";
                }
            }
            return validationMessage;
        }

}
