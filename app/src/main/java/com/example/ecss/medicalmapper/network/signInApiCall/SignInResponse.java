package com.example.ecss.medicalmapper.network.signInApiCall;

import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.model.user.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ecss on 17/05/2017.
 */

public class SignInResponse {

    @SerializedName("status")
    private ApiCallStatus mApiCallStatus;

    @SerializedName("user")
    private User mUser;

    public ApiCallStatus getApiCallStatus() {
        return mApiCallStatus;
    }

    public void setApiCallStatus(ApiCallStatus apiCallStatus) {
        mApiCallStatus = apiCallStatus;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
