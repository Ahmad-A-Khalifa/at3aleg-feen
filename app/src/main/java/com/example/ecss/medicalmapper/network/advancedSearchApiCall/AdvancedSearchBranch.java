package com.example.ecss.medicalmapper.network.advancedSearchApiCall;

import com.google.gson.annotations.SerializedName;


public class AdvancedSearchBranch {

    @SerializedName("place_id")
    private Integer PlaceId;

    @SerializedName("place_name_AR")
    private String PlaceNameAR;

    @SerializedName("place_name_EN")
    private String PlaceNameEN;

    @SerializedName("specialization_AR")
    private String mSpecializationAR;

    @SerializedName("specialization_EN")
    private String mSpecializationEN;

    @SerializedName("branch_id")
    private Integer mBranchId;

    @SerializedName("branch_type")
    private String mBranchType;

    @SerializedName("branch_street_name")
    private String mBranchStreetName;

    @SerializedName("branch_building_num")
    private String mBranchBuildingNum;

    @SerializedName("branch_floor_num")
    private String mBranchFloorNum;

    @SerializedName("branch_apartment_num")
    private String mBranchAppartmentNum;

    @SerializedName("branch_address_notes")
    private String mBranchAddressNotes;

    @SerializedName("branch_longitude")
    private String mBranchLongitude;

    @SerializedName("branch_latitude")
    private String mBranchLatitude;

    @SerializedName("branch_rate")
    private Integer mBranchRate;

    @SerializedName("appointments")
    private String mAppointments;

    @SerializedName("branch_phone_num")
    private String mPhoneNumber;

    public AdvancedSearchBranch (){}

    public Integer getPlaceId() {
        return PlaceId;
    }

    public String getPlaceNameAR() {
        return PlaceNameAR;
    }

    public String getPlaceNameEN() {
        return PlaceNameEN;
    }

    public String getSpecializationAR() {
        return mSpecializationAR;
    }

    public String getSpecializationEN() {
        return mSpecializationEN;
    }

    public Integer getBranchId() {
        return mBranchId;
    }

    public String getBranchType() {
        return mBranchType;
    }

    public String getBranchStreetName() {
        return mBranchStreetName;
    }

    public String getBranchBuildingNum() {
        return mBranchBuildingNum;
    }

    public String getBranchFloorNum() {
        return mBranchFloorNum;
    }

    public String getBranchAppartmentNum() {
        return mBranchAppartmentNum;
    }

    public String getBranchAddressNotes() {
        return mBranchAddressNotes;
    }

    public String getBranchLongitude() {
        return mBranchLongitude;
    }

    public String getBranchLatitude() {
        return mBranchLatitude;
    }

    public Integer getBranchRate() {
        return mBranchRate;
    }

    public String getAppointments() {
        return mAppointments;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }
}