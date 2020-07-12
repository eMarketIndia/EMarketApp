package com.example.emarket.Utils;

import android.os.Build;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Validators {

    private static String regexName = "^[a-zA-Z ]{3,15}";

    private static final String REQUIRED_ERR_MSG = "This is Required";

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
        if(isNull(name)){
            errorMessage = REQUIRED_ERR_MSG;
        }
        else if(!Pattern.matches(regexName,name)){
            errorMessage = "Name should be only alphabets and aleast 3 characters";
        }
//        errorMessage = name.length() > 2 ? "" : "Name must have atleast 3 characters";
//        errorMessage = isNull(name) ? "This is Required" : errorMessage;
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

    public static String isValidDob(String dob, boolean isRequired){
        String errorMessage = "";
        errorMessage = !isNull(dob) && (Validators.getAge(dob) >=18) ? "" : "Age should be greater than 18";
        errorMessage = isNull(dob) && isRequired ? "This is Required":errorMessage;
        return errorMessage;
    }

    private static Integer getAge(String dob){
        String []dobArr = dob.split("/");
        if(dobArr.length > 2){
            LocalDate dateofBirth = LocalDate.of(new Integer(dobArr[2]),new Integer(dobArr[0]),new Integer(dobArr[1]));
            LocalDate now = LocalDate.now();
            Period diff = Period.between(dateofBirth,now);
            return diff.getYears();
        }
        return 0;
    }
}
