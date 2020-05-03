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
import com.example.emarket.LoginActivity;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class MarketAPI {

    private String LOGIN_API = "http://192.168.43.31:8085/market/v1/login";
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
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Login API Error",error.toString());
                        Toast.makeText(ctx,"Something went wrong Try Again",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(objectRequest);
        return  loginMsg[0];
    }
}
