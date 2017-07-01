package com.example.ecss.medicalmapper.network;

import com.google.gson.annotations.SerializedName;


public class ApiCallStatus {

    @SerializedName("is_successful")
    Boolean mIsSuccessful;

    @SerializedName("error_status")
    String mErrorStatus;

    public Boolean getIsSuccessful() {
        return mIsSuccessful;
    }

    public void setIsSuccessful(Boolean successful) {
        mIsSuccessful = successful;
    }

    public String getErrorStatus() {
        return mErrorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        mErrorStatus = errorStatus;
    }
}
