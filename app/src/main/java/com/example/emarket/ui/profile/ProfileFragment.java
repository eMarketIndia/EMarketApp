package com.example.emarket.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.emarket.ProfilePage.ProfileAddress;
import com.example.emarket.ProfilePage.ProfileHome;
import com.example.emarket.ProfilePage.ProfileThree;
import com.example.emarket.R;
import com.example.emarket.Utils.Validators;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        BottomNavigationView profileNav = root.findViewById(R.id.profile_navigation);
        profileNav.setOnNavigationItemSelectedListener(profileNavListner);
        getFragmentManager().beginTransaction().replace(R.id.profile_fragment_container, new ProfileHome()).commit();
        return root;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener profileNavListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.profile_home:
                            selectedFragment = new ProfileHome();
                            break;
                        case R.id.profile_contact:
                            selectedFragment = new ProfileAddress();
                            break;
                        case R.id.profile_setting:
                            selectedFragment = new ProfileThree();
                            break;
                    }
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.profile_fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    public static JsonObject profileHomeSaveAction(JsonObject data){
        String name = "",dob = "",mobileNumber = "",eMailId = "";
        JsonObject errorMessageObject = new JsonObject();
        System.out.println(data);
        try {
            System.out.println(data.get("userName").getAsString());
            name = data.get("userName").getAsString();
            dob = data.get("dob").getAsString();
            mobileNumber = data.get("mobileNumber").getAsString();
            eMailId = data.get("eMailID").getAsString();
        }catch (Exception e){
            Log.e("dataExtraction.ProHome",e.getMessage());
        }
        try {
            System.out.println(name);
            errorMessageObject.addProperty("userNameErr", Validators.isValidName(name));
            errorMessageObject.addProperty("mobileNumberErr",Validators.isValidMobileNumber(mobileNumber));
            errorMessageObject.addProperty("dobErr",Validators.isValidDob(dob,false));
            errorMessageObject.addProperty("eMailIdErr",Validators.isValidEmail(eMailId,false));
        }catch (Exception e){
            Log.e("ProHome.ErrMsg",e.getMessage());
        }

        return errorMessageObject;
    }
}
