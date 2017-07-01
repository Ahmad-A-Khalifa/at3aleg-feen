package com.example.ecss.medicalmapper.network.advancedSearchApiCall;


import com.google.gson.annotations.SerializedName;

public class AdvancedSearchRequest {
    @SerializedName("place_name")
    private String mPlaceName;

    @SerializedName("place_specialization")
    private String mPlaceSpecialization;

    @SerializedName("order")
    private Integer mOrder;

    @SerializedName("latitude")
    private String mLatitude;

    @SerializedName("longitude")
    private String mLongitude;

    public AdvancedSearchRequest(String mPlaceName, String mPlaceSpecialization, Integer mOrder, String mLatitude, String mLongitude) {
        this.mPlaceName = mPlaceName;
        this.mPlaceSpecialization = mPlaceSpecialization;
        this.mOrder = mOrder;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    public String getPlaceName() {
        return mPlaceName;
    }

    public String getPlaceSpecialization() {
        return mPlaceSpecialization;
    }

    public Integer getOrder() {
        return mOrder;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }
}
