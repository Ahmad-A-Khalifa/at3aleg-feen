package com.example.ecss.medicalmapper.network.addPlaceApiCall;

import com.google.gson.annotations.SerializedName;

public class AddPlaceRequest {

    @SerializedName("user_id")
    Integer UserId;

    @SerializedName("place_name")
    String mPlaceName;

    @SerializedName("place_specialization")
    String mPlaceSpecializationEn;

    @SerializedName("building_number")
    String mBuildingNumber;

    @SerializedName("street")
    String mStreet;

    @SerializedName("address_notes")
    String mAddressNotes;

    @SerializedName("phone_number")
    String mPhoneNumber;

    public AddPlaceRequest(Integer userId, String mPlaceName, String mPlaceSpecializationEn, String mBuildingNumber, String mStreet, String mAddressNotes, String mPhoneNumber) {
        UserId = userId;
        this.mPlaceName = mPlaceName;
        this.mPlaceSpecializationEn = mPlaceSpecializationEn;
        this.mBuildingNumber = mBuildingNumber;
        this.mStreet = mStreet;
        this.mAddressNotes = mAddressNotes;
        this.mPhoneNumber = mPhoneNumber;
    }
}
