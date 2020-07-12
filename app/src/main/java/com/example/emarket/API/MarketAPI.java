package com.example.emarket.API;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.emarket.Loader.LoaderDialog;
import com.example.emarket.LoginActivity;
import com.example.emarket.Utils.User;
import com.example.emarket.ui.profile.ProfileViewModel;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class MarketAPI {
    private final String IP_PORT ="http://192.168.225.233:8085/market/v1/";
    private String LOGIN_API = IP_PORT+"login";
    private String REGISTER_API = IP_PORT+"userRegisteration";
    private String PINCODE_API = "http://postalpincode.in/api/pincode/";
    private Context ctx;

    public MarketAPI(Context ctx) {
        this.ctx = ctx;
    }

    public String marketLogin (String userId, String password) throws JSONException {
        final String[] loginMsg = {""};
        JsonObject loginBody = new JsonObject();
        loginBody.addProperty("mobileNumber",userId);
        loginBody.addProperty("password",password);
        RequestQueue requestQueue = Volley.newRequestQueue(this.ctx);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.POST, LOGIN_API, new JSONObject(String.valueOf(loginBody)),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Login API Response",response.toString());
                        try {
                            loginMsg[0] = response.get("message").toString();
                            new LoginActivity().navigateToDashboard(loginMsg[0],ctx);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            LoaderDialog.hideLoader();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        LoaderDialog.hideLoader();
                        Log.e("Login API Error",error.toString());
                        Toast.makeText(ctx,"Something went wrong Try Again",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(objectRequest);
        return  loginMsg[0];
    }

    public void userRegister() throws JSONException {
        final String[] responseMessage = {""};
        JsonObject registerBody = new JsonObject();
        registerBody.addProperty("district", "string");
        registerBody.addProperty("state", "Tamil Nadu");
        registerBody.addProperty("name", User.UserRegistration.getUserName());
        registerBody.addProperty("mobileNumber", User.UserRegistration.getMobileNumber());
        registerBody.addProperty("password", User.UserRegistration.getPassword());
        registerBody.addProperty("emailId", User.UserRegistration.getEmailId());
        RequestQueue registerRequest = Volley.newRequestQueue(this.ctx);
        Log.d("Register API Response","Registration API Calling");
        JsonObjectRequest registerObjectRequest = new JsonObjectRequest(
                Request.Method.POST, REGISTER_API, new JSONObject(String.valueOf(registerBody)),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Register API Response", response.toString());
                        try {
                            responseMessage[0] = response.get("message").toString();
                            Toast.makeText(ctx, responseMessage[0], Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Register API Error", error.toString());
                        Toast.makeText(ctx, "Something went wrong Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        registerRequest.add(registerObjectRequest);
    }

    public void getStateUsingPincode() throws Exception {
        String API_URL = PINCODE_API+ ProfileViewModel.getPinCode();
        System.out.println(API_URL);
        JsonObject jsonObject = new JsonObject();
        RequestQueue requestQueue = Volley.newRequestQueue(this.ctx);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, API_URL, new JSONObject(String.valueOf(jsonObject)),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("PINCODE API", response.toString());
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("PINCODE API","Error Occured : "+error.toString());
                        System.out.println(error.toString());
                    }
                }
        );
        requestQueue.add(objectRequest);
    }
}
