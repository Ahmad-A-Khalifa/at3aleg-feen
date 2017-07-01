package com.example.ecss.medicalmapper.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ecss on 17/05/2017.
 */

public class SignInRequest {

    @SerializedName("email")
    private String mEmail;

    @SerializedName("password")
    private String mPassword;

    public SignInRequest(String email, String password) {
        mEmail = email;
        mPassword = password;
    }
}
