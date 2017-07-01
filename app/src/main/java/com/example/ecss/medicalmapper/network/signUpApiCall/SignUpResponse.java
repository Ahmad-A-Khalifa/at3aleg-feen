package com.example.ecss.medicalmapper.network.signUpApiCall;

import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.model.user.User;
import com.google.gson.annotations.SerializedName;



public class SignUpResponse {

    @SerializedName("status")
    private ApiCallStatus mApiCallStatus;

    @SerializedName("user")
    private User user;

    public ApiCallStatus getApiCallStatus() {
        return mApiCallStatus;
    }

    public void setApiCallStatus(ApiCallStatus apiCallStatus) {
        this.mApiCallStatus = apiCallStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
