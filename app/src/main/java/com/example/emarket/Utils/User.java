package com.example.emarket.Utils;

public class User {

    private static String userName;
    private static boolean isLoggedIn;
    private static String userId;

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

}
