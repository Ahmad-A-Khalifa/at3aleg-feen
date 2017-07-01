package com.example.ecss.medicalmapper.network.showNearbyPlacesApiCall;

import com.google.gson.annotations.SerializedName;


public class ShowNearbyPlacesRequest {

    @SerializedName("language")
    private Integer mLanguage;

    @SerializedName("cardinal_points")
    private CardinalPoints mCardinalPoints;

    @SerializedName("places_type")
    private Integer mPlacesType;

    public ShowNearbyPlacesRequest(Integer language, CardinalPoints cardinalPoints, Integer placesType) {
        mLanguage = language;
        mCardinalPoints = cardinalPoints;
        mPlacesType = placesType;
    }
}
