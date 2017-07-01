package com.example.ecss.medicalmapper.network.advancedSearchApiCall;

import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.google.gson.annotations.SerializedName;

public class AdvancedSearchResponse {
    @SerializedName("status")
    ApiCallStatus mStatus;

    @SerializedName("places")
    AdvancedSearchPlaces mPlaces;

    public ApiCallStatus getApiCallStatus() {
        return mStatus;
    }

    public AdvancedSearchPlaces getPlaces() {
        return mPlaces;
    }
}
