package com.example.emarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.emarket.Utils.User;
import com.example.emarket.Utils.Validators;

public class RegistrationSecondFragment extends Fragment {

    private EditText mobileNumber, eMail;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.registration_contact, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mobileNumber = view.findViewById(R.id.mobileNumber);
        eMail = view.findViewById(R.id.eMail);

        view.findViewById(R.id.continue_to_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobileNum = mobileNumber.getText().toString();
                String emailId = eMail.getText().toString();
                if (validateFields(mobileNum, emailId)) {
                    User.UserRegistration.setEmailId(emailId);
                    User.UserRegistration.setMobileNumber(mobileNum);
                    NavHostFragment.findNavController(RegistrationSecondFragment.this)
                            .navigate(R.id.action_contact_to_password);
                }
            }
        });

        view.findViewById(R.id.contact_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

    }

    private boolean validateFields(String mNumber, String emailId) {
        String mNumberErrTxt = Validators.isValidMobileNumber(mNumber);
        String emailIdErrTxt = Validators.isValidEmail(emailId, false);
        Validators.setEditFieldErrMsg(mNumberErrTxt, mobileNumber);
        Validators.setEditFieldErrMsg(emailIdErrTxt, eMail);
        return mNumberErrTxt.equals("") && emailIdErrTxt.equals("");
    }
}
