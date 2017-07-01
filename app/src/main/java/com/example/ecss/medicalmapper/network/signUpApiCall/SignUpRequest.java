package com.example.ecss.medicalmapper.network.signUpApiCall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sheri on 5/28/2017.
 */

public class SignUpRequest {

    @SerializedName("user_name")
    private String mUserName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("password")
    private String mPassword;


    public SignUpRequest(String userName,String email, String password ) {
        mEmail = email;
        mPassword = password;
        mUserName = userName;
    }
}
