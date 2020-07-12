package com.example.emarket.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SavedPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     *
     * @param context
     * @param loggedIn
     */
    public static void setLoggedInStatus(Context context, boolean loggedIn, String userName, String userId) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        if (userName.length() > 0 && userId.length() > 0) {
            editor.putBoolean(Constants.LOGIN_STATUS, loggedIn);
            editor.putString(Constants.USER_NAME, userName);
            editor.putString(Constants.USER_ID, userId);
        } else {
            editor.putBoolean(Constants.LOGIN_STATUS, false);
            editor.putString(Constants.USER_NAME, "");
            editor.putString(Constants.USER_ID, "");
        }
        editor.apply();
    }

    public static boolean getLoggedInStatus(Context context) {
        return getPreferences(context).getBoolean(Constants.LOGIN_STATUS, false);
    }

    public static String[] getLoggedInUser(Context context) {
        final String userName = getPreferences(context).getString(Constants.USER_NAME, "User");
        final String userId = getPreferences(context).getString(Constants.USER_ID, "");
        return new String[]{userId, userName};
    }
}
