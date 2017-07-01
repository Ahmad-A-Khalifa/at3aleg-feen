package com.example.ecss.medicalmapper.model;

import com.example.ecss.medicalmapper.model.place.MedicalPlaces;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ecss on 13/06/2017.
 */

public class PlacesResponse {

    @SerializedName("status")
    private ApiCallStatus mApiCallStatus;

    @SerializedName("places")
    private MedicalPlaces mMedicalPlaces;

    public ApiCallStatus getApiCallStatus() {
        return mApiCallStatus;
    }

    public void setApiCallStatus(ApiCallStatus apiCallStatus) {
        mApiCallStatus = apiCallStatus;
    }

    public MedicalPlaces getMedicalPlaces() {
        return mMedicalPlaces;
    }

    public void setMedicalPlaces(MedicalPlaces medicalPlaces) {
        mMedicalPlaces = medicalPlaces;
    }
}
