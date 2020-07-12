package com.example.emarket.Utils;

public class User {

    private static String userName,mNumberVerified,eMailVerified;
    private static boolean isLoggedIn, loaderState;
    private static String userId;
    private static String mobileNumber ="", eMail ="", dob ="";

    public static String getMobileNumber() {
        return mobileNumber;
    }

    public static void setMobileNumber(String mobileNumber) {
        User.mobileNumber = mobileNumber;
    }

    public static String geteMail() {
        return eMail;
    }

    public static void seteMail(String eMail) {
        User.eMail = eMail;
    }

    public static String getDob() {
        return dob;
    }

    public static void setDob(String dob) {
        User.dob = dob;
    }

    public static String getEmailVerifyStatus() {
        return emailVerifyStatus;
    }

    public static void setEmailVerifyStatus(String emailVerifyStatus) {
        User.emailVerifyStatus = emailVerifyStatus;
    }

    private static String emailVerifyStatus;

    public static boolean getLoaderState() {
        return loaderState;
    }

    public static void setLoaderState(boolean loaderState) {
        User.loaderState = loaderState;
    }

    public static String getmNumberVerified() {
        return mNumberVerified;
    }

    public static void setmNumberVerified(String mNumberVerified) {
        User.mNumberVerified = mNumberVerified;
    }

    public static String geteMailVerified() {
        return eMailVerified;
    }

    public static void seteMailVerified(String eMailVerified) {
        User.eMailVerified = eMailVerified;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        User.userId = userId;
    }

    public static boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(boolean isLoggedIn) {
        User.isLoggedIn = isLoggedIn;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        userName = userName.toUpperCase();
        User.userName = userName;
    }

    public static class UserRegistration {
        private static String userName, password, mobileNumber, emailId;

        public static String getUserName() {
            return userName;
        }

        public static void setUserName(String userName) {
            UserRegistration.userName = userName;
        }

        public static String getPassword() {
            return password;
        }

        public static void setPassword(String password) {
            UserRegistration.password = password;
        }

        public static String getMobileNumber() {
            return mobileNumber;
        }

        public static void setMobileNumber(String mobileNumber) {
            UserRegistration.mobileNumber = mobileNumber;
        }

        public static String getEmailId() {
            return emailId;
        }

        public static void setEmailId(String emailId) {
            UserRegistration.emailId = emailId;
        }


    }

    public static class UserProfile {
        private static String pinCode;

        public static String getPinCode() {
            return pinCode;
        }

        public static void setPinCode(String pinCode) {
            UserProfile.pinCode = pinCode;
        }
    }

}
