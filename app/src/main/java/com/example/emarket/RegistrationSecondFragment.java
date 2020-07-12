package com.example.emarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.emarket.Loader.LoaderDialog;
import com.example.emarket.Utils.User;
import com.example.emarket.Utils.Validators;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegistrationSecondFragment extends Fragment {

    private EditText mobileNumber, eMail;
    private Button sendOTP;




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
        sendOTP = view.findViewById(R.id.btn_send_otp);

        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNum = mobileNumber.getText().toString();
                String emailId = eMail.getText().toString();
                if (validateFields(mobileNum, emailId)) {
                    LoaderDialog.showLoader(getActivity());
                    User.UserRegistration.setEmailId(emailId);
                    User.UserRegistration.setMobileNumber(mobileNum);
                    navigateToOTPVerification();
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

    public void navigateToOTPVerification(){
        NavHostFragment.findNavController(RegistrationSecondFragment.this)
                .navigate(R.id.action_contact_to_otp_verification);
    }


    private boolean validateFields(String mNumber, String emailId) {
        String mNumberErrTxt = Validators.isValidMobileNumber(mNumber);
        String emailIdErrTxt = Validators.isValidEmail(emailId, false);
        Validators.setEditFieldErrMsg(mNumberErrTxt, mobileNumber);
        Validators.setEditFieldErrMsg(emailIdErrTxt, eMail);
        return mNumberErrTxt.equals("") && emailIdErrTxt.equals("");
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        if(FirebaseAuth.getInstance().getCurrentUser() != null){
//            Intent intent = new Intent(getContext(),RegistrationActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }


}
