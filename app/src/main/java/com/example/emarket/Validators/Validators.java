package com.example.emarket.Validators;

public class Validators {

    public static boolean isNull (String value) {
        return value.length() == 0;
    }

    public static String isValidMobileNumber(String mobileNumber){
         String errorMessage = "";
         errorMessage = mobileNumber.length() == 10?"":"Not a valid Mobile Number";
         errorMessage = isNull(mobileNumber)?"This is Required":errorMessage;
         return errorMessage;
    }

    public static String isValidPassword(String password){
        String errorMessage = "";
        errorMessage = password.length() > 5 ?"":"Password should atleast have 6 characters";
        errorMessage = isNull(password)?"This is Required":errorMessage;
        return errorMessage;
    }
}
