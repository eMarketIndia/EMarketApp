package com.example.emarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emarket.API.MarketAPI;
import com.example.emarket.Validators.Validators;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    private EditText UserId, Password;
    private TextView RegisterHere;
    private Button SellerLogin, BuyerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserId = (EditText) findViewById(R.id.userId);
        Password = (EditText) findViewById(R.id.password);
        SellerLogin = (Button) findViewById(R.id.sellerLogin);
        BuyerLogin = (Button) findViewById(R.id.buyerLogin);
        RegisterHere = (TextView) findViewById(R.id.registerLink);

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
        if (responseMessage.startsWith("Welcome")){
            String[] loginMsg = responseMessage.split(" ");
            User.setUserName(loginMsg[1]);
            ctx.startActivity(new Intent(ctx,SellerDashboard.class));
        }
        else {
            Toast.makeText(ctx,responseMessage,Toast.LENGTH_SHORT).show();
        }
    }

}
