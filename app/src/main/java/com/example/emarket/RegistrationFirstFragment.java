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

public class RegistrationFirstFragment extends Fragment {

    private EditText UserName;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.registration_name, container, false);
        UserName = root.findViewById(R.id.userName);
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.continue_to_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = UserName.getText().toString();
                if (validateFields(name)) {
                    User.UserRegistration.setUserName(name);
                    NavHostFragment.findNavController(RegistrationFirstFragment.this)
                            .navigate(R.id.action_name_to_contact);
                }
            }
        });
        view.findViewById(R.id.name_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
    }

    private boolean validateFields(String name) {
        String userNameErrTxt = Validators.isValidName(name);
        Validators.setEditFieldErrMsg(userNameErrTxt, UserName);
        return userNameErrTxt.equals("");
    }

}
