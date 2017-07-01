package com.example.ecss.medicalmapper.network;

import com.example.ecss.medicalmapper.model.user.Doctor;
import com.google.gson.annotations.SerializedName;

/**
 * Created by E C S on 22/06/2017.
 */

public class GetPremiumDataResponse {

    @SerializedName("status")
    private ApiCallStatus mApiCallStatus;

    @SerializedName("doctor_info")
    private Doctor mDoctor;

    public ApiCallStatus getApiCallStatus() {
        return mApiCallStatus;
    }

    public void setApiCallStatus(ApiCallStatus mApiCallStatus) {
        this.mApiCallStatus = mApiCallStatus;
    }

    public Doctor getDoctor() {
        return mDoctor;
    }

    public void setDoctor(Doctor mDoctor) {
        this.mDoctor = mDoctor;
    }
}
