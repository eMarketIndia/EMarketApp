package com.example.emarket;

import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emarket.Loader.LoaderDialog;
import com.example.emarket.Utils.Constants;
import com.example.emarket.Utils.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;



public class OtpVerification extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private final String VERIFY_OTP = "Verify OTP";
    private final String RESEND_OTP = "Resend OTP";
    private final String NEXT = "Next";

    private FirebaseAuth mAuth;
    private static String mobileNumber,eMailId;
    private String verificationCode;

    private EditText mobileOtp;
    private Button next, back;
    private LinearLayout emailLayout;
    private TextView eMailAck;

    public OtpVerification() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        mobileNumber = User.UserRegistration.getMobileNumber();
        eMailId = User.UserRegistration.getEmailId();

        if(!eMailId.equals("")){
            ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder().setUrl("https://www.google.co.in/")
                    .setHandleCodeInApp(true).setIOSBundleId("com.example.emarket").setAndroidPackageName(
                            "com.example.emarket",
                            true,
                            "12"
                    ).build();
            mAuth.sendSignInLinkToEmail(User.UserRegistration.getEmailId(), actionCodeSettings)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Log.d("Email Verification","Email Send for Verification");
//                            Toast.makeText(getContext(),"Email Send for Verification",Toast.LENGTH_LONG).show();
                                User.setEmailVerifyStatus(Constants.EMAIL_SEND);
                            }
                            else {
                                Log.e("Email Verification",task.getException().getMessage());
                                Toast.makeText(getContext(),"Something went Wrong cannot send Verification Email",Toast.LENGTH_LONG).show();
                                User.setEmailVerifyStatus(Constants.ERR_IN_SEND_EMAIL);
                            }
                        }
                    });

        }
        sendVerificationCode("+91"+mobileNumber);
        return inflater.inflate(R.layout.fragment_otp_verification, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eMailAck = view.findViewById(R.id.email_ack);
        mobileOtp = view.findViewById(R.id.mobile_otp);
        emailLayout = view.findViewById(R.id.email_otp_layout);
        back = view.findViewById(R.id.back_to_contact);
        next = view.findViewById(R.id.verify_and_continue);

        LoaderDialog.hideLoader();

        if(eMailId.equals("")){
            emailLayout.setVisibility(View.INVISIBLE);
        }
        else {
            emailLayout.setVisibility(View.VISIBLE);
            eMailAck.setText(User.getEmailVerifyStatus());
            if(User.getEmailVerifyStatus().equals(Constants.EMAIL_SEND)){
                eMailAck.setTextColor(Integer.parseInt("#03801e"));
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(OtpVerification.this).navigate(R.id.action_back_to_contact);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileOtp.setError("");
                String buttonText = next.getText().toString().trim();
                if(buttonText.equals(VERIFY_OTP)){
                    String otpTextMobile = mobileOtp.getText().toString().trim();
                    System.out.println(otpTextMobile);
                    if(otpTextMobile.length() == 6){
                        Toast.makeText(getContext(),"Verifying OTP",Toast.LENGTH_SHORT).show();
                        verifyCode(otpTextMobile);
                    }
                    else {
                        mobileOtp.setError("OTP should be 6 digits");
                        mobileOtp.requestFocus();
                    }
                }
                else if(buttonText.equals(RESEND_OTP)){
                    sendVerificationCode("+91"+mobileNumber);
                }
                else if(buttonText.equals(NEXT)){
                    navigateToPasswordFragment();
                }
            }
        });
    }

    private void sendVerificationCode(String mobileNum) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(mobileNum,60, TimeUnit.SECONDS, getActivity(),mCallback);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code != null) {
                mobileOtp.setText(code);
                verifyCode(code);
            }
        }


        @Override
        public void onVerificationFailed(FirebaseException e) {
            System.out.println(e);
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            next.setText(RESEND_OTP);
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            Toast.makeText(getContext(),"OTP sent",Toast.LENGTH_SHORT).show();
            super.onCodeSent(s, forceResendingToken);
            verificationCode = s;
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(final PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(),"OTP Verified Successfully",Toast.LENGTH_LONG).show();
                    next.setText("Next");
                    navigateToPasswordFragment();
                }
                else {
                    Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    next.setText("Resend OTP");
                }
            }
        });
    }

    private void navigateToPasswordFragment(){
        NavHostFragment.findNavController(OtpVerification.this).navigate(R.id.action_verify_otp_to_password);
    }
}
