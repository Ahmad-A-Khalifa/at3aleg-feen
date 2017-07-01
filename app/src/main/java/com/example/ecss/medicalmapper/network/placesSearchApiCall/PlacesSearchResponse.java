package com.example.ecss.medicalmapper.network.placesSearchApiCall;

import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlacesSearchResponse {

    @SerializedName("status")
    private ApiCallStatus mApiCallStatus;

    @SerializedName("branchs")
    private List<PlacesSearchBranch> mBranches;

    public ApiCallStatus getApiCallStatus() {
        return mApiCallStatus;
    }

    public void setApiCallStatus(ApiCallStatus mApiCallStatus) {
        this.mApiCallStatus = mApiCallStatus;
    }

    public List<PlacesSearchBranch> getBranches() {
        return mBranches;
    }

    public void setBranches(List<PlacesSearchBranch> mBranches) {
        this.mBranches = mBranches;
    }
}
