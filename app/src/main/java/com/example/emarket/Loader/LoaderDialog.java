package com.example.emarket.Loader;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.emarket.R;
import com.example.emarket.Utils.User;

public class LoaderDialog {

    private static AlertDialog alertDialog;


    public static void showLoader(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loader_dialog,null));
        builder.setCancelable(false);
        User.setLoaderState(true);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public static void hideLoader(){
        User.setLoaderState(false);
        alertDialog.dismiss();
    }
}
