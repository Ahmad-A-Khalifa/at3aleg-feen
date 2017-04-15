package com.example.ecss.medicalmapper.model.Place;

import com.example.ecss.medicalmapper.model.Appointment;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;


public class Branch extends RealmObject {

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

    @SerializedName("branch_phone_num")
    private String mBranchPhoneNum;

    @SerializedName("branch_longitude")
    private String mBranchLongitude;

    @SerializedName("branch_latitude")
    private String mBranchLatitude;

    @SerializedName("branch_rate")
    private Integer mBranchRate;

    @SerializedName("Appointments")
    RealmList<Appointment> mAppointments;

    public Integer getmBranchId() {
        return mBranchId;
    }

    public void setmBranchId(Integer mBranchId) {
        this.mBranchId = mBranchId;
    }

    public String getmBranchType() {
        return mBranchType;
    }

    public void setmBranchType(String mBranchType) {
        this.mBranchType = mBranchType;
    }

    public String getmBranchStreetName() {
        return mBranchStreetName;
    }

    public void setmBranchStreetName(String mBranchStreetName) {
        this.mBranchStreetName = mBranchStreetName;
    }

    public String getmBranchBuildingNum() {
        return mBranchBuildingNum;
    }

    public void setmBranchBuildingNum(String mBranchBuildingNum) {
        this.mBranchBuildingNum = mBranchBuildingNum;
    }

    public String getmBranchFloorNum() {
        return mBranchFloorNum;
    }

    public void setmBranchFloorNum(String mBranchFloorNum) {
        this.mBranchFloorNum = mBranchFloorNum;
    }

    public String getmBranchAppartmentNum() {
        return mBranchAppartmentNum;
    }

    public void setmBranchAppartmentNum(String mBranchAppartmentNum) {
        this.mBranchAppartmentNum = mBranchAppartmentNum;
    }

    public String getmBranchAddressNotes() {
        return mBranchAddressNotes;
    }

    public void setmBranchAddressNotes(String mBranchAddressNotes) {
        this.mBranchAddressNotes = mBranchAddressNotes;
    }

    public String getmBranchPhoneNum() {
        return mBranchPhoneNum;
    }

    public void setmBranchPhoneNum(String mBranchPhoneNum) {
        this.mBranchPhoneNum = mBranchPhoneNum;
    }

    public String getmBranchLongitude() {
        return mBranchLongitude;
    }

    public void setmBranchLongitude(String mBranchLongitude) {
        this.mBranchLongitude = mBranchLongitude;
    }

    public String getmBranchLatitude() {
        return mBranchLatitude;
    }

    public void setmBranchLatitude(String mBranchLatitude) {
        this.mBranchLatitude = mBranchLatitude;
    }

    public Integer getmBranchRate() {
        return mBranchRate;
    }

    public void setmBranchRate(Integer mBranchRate) {
        this.mBranchRate = mBranchRate;
    }

    public RealmList<Appointment> getmAppointments() {
        return mAppointments;
    }

    public void setmAppointments(RealmList<Appointment> mAppointments) {
        this.mAppointments = mAppointments;
    }
}
