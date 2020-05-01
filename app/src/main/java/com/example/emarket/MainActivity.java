package com.example.emarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emarket.Validators.Validators;

public class MainActivity extends AppCompatActivity {

    private EditText UserId, Password;
    private TextView RegisterHere;
    private Button SellerLogin, BuyerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserId = (EditText) findViewById(R.id.userId);
        Password = (EditText) findViewById(R.id.password);
        SellerLogin = (Button) findViewById(R.id.sellerLogin);
        BuyerLogin = (Button) findViewById(R.id.buyerLogin);

        SellerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateFields(UserId.getText().toString(),Password.getText().toString())){
                    startActivity(new Intent(MainActivity.this,SellerDashboard.class));
                }
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
}
