package com.digicore.digicore_banking_web_application.utility;

public class Validator {
    /**
     * ^ - start of string
     * $ - end of string.
     * @param email
     * @return boolean
     * */
    public static boolean validateEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    /**
     * Allows password only greater than 5 in length
     * @param password
     * @return boolean
     * */
    public static boolean validatePassword(String password){
        if(password.length() > 5) return true;
        return false;
    }
    /**
     * ^ - start of string
     * [a-zA-Z]{3,} - 3 or more ASCII letters
     * (?: [a-zA-Z]+){1,2} - 1 to 2 occurrences of a space followed with one or more ASCII letters
     * $ - end of string.
     * @param fullName
     * @return boolean
     * */
    public static boolean validateFullName(String fullName){
        String regex = "^[a-zA-Z]{3,}(?: [a-zA-Z]+){1,2}$";
        return fullName.matches(regex);
    }
    /**
     * ^ - start of string
     * [a-zA-Z]{4,} - 4 or more ASCII letters
     * $ - end of string.
     * @param username
     * @return boolean
     * */
    public static boolean validateUserName(String username){
        String regex = "^[a-zA-Z]{3,}$";
        return username.matches(regex);
    }
    /**
     * Should return false if vendor role is supplied without the referral phone number
     * @param role
     * @param referralPhoneNumber
     * @return boolean
     * */
    public static boolean validateVendorReq(String role, String referralPhoneNumber){
        if(role.equals("vendor"))
            if(referralPhoneNumber != null)
                if(referralPhoneNumber.length() == 0) return false;
                else
                    return false;
        return true;
    }
    /**
     * Should return false phone number is not in the accepted format
     * for 0, 234, +234
     * @param phoneNumber
     * @return boolean
     * */
    public static boolean validatePhoneNumber(String phoneNumber){
        String regex = "(^[0]\\d{10}$)|(^[\\+]?[234]\\d{12})";
        return phoneNumber.matches(regex);
    }
}
