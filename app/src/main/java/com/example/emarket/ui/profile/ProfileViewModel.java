package com.example.emarket.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private static String userName, dob, mobileNumber, eMailId, doorNo, addrLine1, addrLine2, city, state, country;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        ProfileViewModel.userName = userName;
    }

    public static String getDob() {
        return dob;
    }

    public static void setDob(String dob) {
        ProfileViewModel.dob = dob;
    }

    public static String getMobileNumber() {
        return mobileNumber;
    }

    public static void setMobileNumber(String mobileNumber) {
        ProfileViewModel.mobileNumber = mobileNumber;
    }

    public static String geteMailId() {
        return eMailId;
    }

    public static void seteMailId(String eMailId) {
        ProfileViewModel.eMailId = eMailId;
    }

    public static String getDoorNo() {
        return doorNo;
    }

    public static void setDoorNo(String doorNo) {
        ProfileViewModel.doorNo = doorNo;
    }

    public static String getAddrLine1() {
        return addrLine1;
    }

    public static void setAddrLine1(String addrLine1) {
        ProfileViewModel.addrLine1 = addrLine1;
    }

    public static String getAddrLine2() {
        return addrLine2;
    }

    public static void setAddrLine2(String addrLine2) {
        ProfileViewModel.addrLine2 = addrLine2;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        ProfileViewModel.city = city;
    }

    public static String getState() {
        return state;
    }

    public static void setState(String state) {
        ProfileViewModel.state = state;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        ProfileViewModel.country = country;
    }

    public static String getPinCode() {
        return pinCode;
    }

    public static void setPinCode(String pinCode) {
        ProfileViewModel.pinCode = pinCode;
    }

    private static String pinCode;
}