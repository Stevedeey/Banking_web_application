package com.digicore.digicore_banking_web_application.payload.auth;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class LoginResponse {

    private String type;

    private boolean success;

    private String accessToken;


    public LoginResponse(boolean success, String accessToken) {

        this.success = success;
        this.accessToken = accessToken;
        this.type = "Bearer";

    }

    public LoginResponse() {
        this.type = "Bearer";
    }

    public LoginResponse(String type, boolean success, String accessToken) {
        this.type = type;
        this.success = success;
        this.accessToken = accessToken;
    }


}
