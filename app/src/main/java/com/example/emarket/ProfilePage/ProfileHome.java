package com.example.emarket.ProfilePage;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.emarket.R;
import com.example.emarket.Utils.User;
import com.example.emarket.ui.profile.ProfileFragment;
import com.google.gson.JsonObject;

import java.util.Calendar;

public class ProfileHome extends Fragment {

    private EditText dob;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private EditText mobileNumber;
    private EditText eMail;
    private EditText profileUserName;
    private Button save,discard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Mapping view elements to a class variable
        profileUserName = view.findViewById(R.id.profile_home_username);
        final ImageButton editName = view.findViewById(R.id.edit_name);
        dob = (EditText) view.findViewById(R.id.profile_home_dob);
        mobileNumber = (EditText) view.findViewById(R.id.profile_home_mobileNumber);
        eMail = (EditText) view.findViewById(R.id.profile_home_eMail);
        save = (Button) view.findViewById(R.id.profile_home_save);
        discard = (Button) view.findViewById(R.id.profile_home_discard);

        //Initialize the Edit fields

        mobileNumber.setText(User.getMobileNumber());
        eMail.setText(User.geteMail());
        profileUserName.setText(User.getUserName());
        dob.setText(User.getDob());


        //onClickEvents
        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileUserName.setEnabled(true);
                profileUserName.setFocusable(true);
            }
        });
        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Calendar calendar  = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dobPicker = new DatePickerDialog(getContext(),dateSetListener,year,month,day);
                    dobPicker.show();
                }
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String dateOfBirth = (month+1)+"/"+dayOfMonth+"/"+year;
                dob.setText(dateOfBirth);
                Log.d("ProfileHome","DOB set as "+dateOfBirth);
                Toast.makeText(getContext(),"DOB set as "+dateOfBirth,Toast.LENGTH_LONG).show();
            }
        };

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileNumber.setText(User.getMobileNumber());
                eMail.setText(User.geteMail());
                profileUserName.setText(User.getUserName());
                dob.setText(User.getDob());
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObject profileHomeData = new JsonObject();
                profileHomeData.addProperty("userName",profileUserName.getText().toString());
                profileHomeData.addProperty("mobileNumber",mobileNumber.getText().toString());
                profileHomeData.addProperty("eMailID",eMail.getText().toString());
                profileHomeData.addProperty("dob",dob.getText().toString());
                JsonObject errorMessages = ProfileFragment.profileHomeSaveAction(profileHomeData);
                System.out.println(errorMessages);
                Toast.makeText(getContext(),"Changes Saved!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
