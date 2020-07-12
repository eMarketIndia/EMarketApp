package com.example.emarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.emarket.API.MarketAPI;
import com.example.emarket.Utils.User;
import com.example.emarket.Utils.Validators;

import org.json.JSONException;

public class RegistrationThirdFragment extends Fragment {

    private EditText password, confrmPassword;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.registration_password, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        password = view.findViewById(R.id.registration_password);
        confrmPassword = view.findViewById(R.id.cnfPassword);

        view.findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields(password.getText().toString(), confrmPassword.getText().toString())) {
                    User.UserRegistration.setPassword(password.getText().toString());
                    Toast.makeText(getContext(), "Registering You", Toast.LENGTH_SHORT).show();
                    try {
                        new MarketAPI(getContext()).userRegister();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        view.findViewById(R.id.password_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });


    }

    private boolean validateFields(String pass, String cnfPass) {
        String passErrTxt = Validators.isValidPassword(pass);
        String cnfPassErrTxt = Validators.isValidPassword(cnfPass);
        cnfPassErrTxt = pass.equals(cnfPass) ? cnfPassErrTxt : "Confirm Password is not matched with Password";
        Validators.setEditFieldErrMsg(passErrTxt, password);
        Validators.setEditFieldErrMsg(cnfPassErrTxt, confrmPassword);
        return passErrTxt.equals("") && cnfPassErrTxt.equals("");
    }

    public void onCancel() {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
