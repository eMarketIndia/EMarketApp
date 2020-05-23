package com.example.emarket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.emarket.API.MarketAPI;
import com.example.emarket.Utils.SavedPreference;
import com.example.emarket.Utils.User;
import com.example.emarket.Utils.Validators;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    private EditText UserId, Password;
    private TextView RegisterHere;
    private Button SellerLogin, BuyerLogin;
    static TextView ErrorLabel;
    private static int backButtonCount = 0;
    private ConstraintLayout loginForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserId = (EditText) findViewById(R.id.userId);
        Password = (EditText) findViewById(R.id.password);
        SellerLogin = (Button) findViewById(R.id.sellerLogin);
        BuyerLogin = (Button) findViewById(R.id.buyerLogin);
        RegisterHere = (TextView) findViewById(R.id.registerLink);
        ErrorLabel = (TextView) findViewById(R.id.errorLabel);
        loginForm = findViewById(R.id.loginForm);

        if (SavedPreference.getLoggedInStatus(LoginActivity.this)) {
            System.out.println("Already Logged In");
            String[] userDetails = SavedPreference.getLoggedInUser(this);
            User.setUserId(userDetails[0]);
            User.setUserName(userDetails[1]);
            navigateToDashboard("Welcome " + User.getUserName(), LoginActivity.this);
        }

        SellerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = UserId.getText().toString(), password = Password.getText().toString();
                if(validateFields(userId,password)){
                    try {
                        new MarketAPI(LoginActivity.this).marketLogin(userId,password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        RegisterHere.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });
    }

    private boolean validateFields(String userId, String password){
        boolean isValid = true;
        String userIdErrorTxt = Validators.isValidMobileNumber(userId);
        String passwordErrorTxt = Validators.isValidPassword(password);
        if(userIdErrorTxt!=""){
            isValid = false;
            UserId.setError(userIdErrorTxt);
        }
        else {
            UserId.setError(null);
        }
        if(passwordErrorTxt!=""){
            isValid = false;
            Password.setError(passwordErrorTxt);
        }else {
            Password.setError(null);
        }
        return isValid;
    }

    public void navigateToDashboard(String responseMessage, Context ctx) {
        try {
            ErrorLabel.setText(null);
            ErrorLabel.setVisibility(View.GONE);
        } catch (NullPointerException e) {
            Log.println(Log.WARN, "Null Exception", "Cannot reset errorlable");
        }
        if (responseMessage.startsWith("Welcome")){
            String[] loginMsg = responseMessage.split(" ");
            System.out.println(responseMessage);
            User.setUserName(loginMsg[1]);
            String userId = loginMsg.length > 2 ? loginMsg[2] : "123456789";
            SavedPreference.setLoggedInStatus(ctx, true, loginMsg[1], userId);
            ctx.startActivity(new Intent(ctx,SellerDashboard.class));
        }
        else {
            ErrorLabel.setVisibility(View.VISIBLE);
            ErrorLabel.setText(responseMessage);
            Toast.makeText(ctx,responseMessage,Toast.LENGTH_SHORT).show();
        }
    }
}
