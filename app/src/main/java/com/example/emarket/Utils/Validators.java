package com.example.emarket.Utils;

import android.widget.EditText;

public class Validators {

    public static boolean isNull(String value) {
        return value.length() == 0;
    }

    public static String isValidMobileNumber(String mobileNumber) {
        String errorMessage = "";
        errorMessage = mobileNumber.length() == 10 ? "" : "Not a valid Mobile Number";
        errorMessage = isNull(mobileNumber) ? "This is Required" : errorMessage;
        return errorMessage;
    }

    public static String isValidPassword(String password) {
        String errorMessage = "";
        errorMessage = password.length() > 5 ? "" : "Password should have atleast 6 characters";
        errorMessage = isNull(password) ? "This is Required" : errorMessage;
        return errorMessage;
    }

    public static String isValidName(String name) {
        String errorMessage = "";
        errorMessage = name.length() > 2 ? "" : "Name must have atleast 3 characters";
        errorMessage = isNull(name) ? "This is Required" : errorMessage;
        return errorMessage;
    }

    public static void setEditFieldErrMsg(String errorMsg, EditText fieldName) {
        if (!errorMsg.equals("")) {
            fieldName.setError(errorMsg);
        } else {
            fieldName.setError(null);
        }
    }

    public static String isValidEmail(String email, boolean isRequired) {
        String errorMessage = "";
        errorMessage = email.isEmpty() || email.contains("@") ? "" : "Invalid Email ID";
        errorMessage = isNull(email) && isRequired ? "This is Required" : errorMessage;
        return errorMessage;
    }
}
