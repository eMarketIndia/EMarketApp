package com.example.emarket.ProfilePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.emarket.API.MarketAPI;
import com.example.emarket.R;
import com.example.emarket.ui.profile.ProfileViewModel;

public class ProfileAddress extends Fragment {

    private EditText pinCode;
    private Button saveAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pinCode = (EditText) view.findViewById(R.id.profile_address_pincode);
        saveAddress = (Button) view.findViewById(R.id.profile_address_save_btn);

        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"PinCode: "+pinCode.getText().toString(),Toast.LENGTH_LONG).show();
                ProfileViewModel.setPinCode(pinCode.getText().toString());
                try {
                    new MarketAPI(getContext()).getStateUsingPincode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
