package com.example.ecss.medicalmapper.model.place;

import com.example.ecss.medicalmapper.model.appointment.Appointment;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;


public class Branch extends RealmObject {

    @SerializedName("Appointments")
    RealmList<Appointment> mAppointments;
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

    private Branch() {

    }

    public Branch(BranchBuilder branchBuilder) {
        this.mBranchId = branchBuilder.mBranchId;
        this.mBranchType = branchBuilder.mBranchType;
        this.mBranchStreetName = branchBuilder.mBranchStreetName;
        this.mBranchBuildingNum = branchBuilder.mBranchBuildingNum;
        this.mBranchFloorNum = branchBuilder.mBranchFloorNum;
        this.mBranchAppartmentNum = branchBuilder.mBranchAppartmentNum;
        this.mBranchAddressNotes = branchBuilder.mBranchAddressNotes;
        this.mBranchPhoneNum = branchBuilder.mBranchPhoneNum;
        this.mBranchLongitude = branchBuilder.mBranchLongitude;
        this.mBranchLatitude = branchBuilder.mBranchLatitude;
        this.mBranchRate = branchBuilder.mBranchRate;
        this.mAppointments = branchBuilder.mAppointments;
    }

    public Integer getmBranchId() {
        return mBranchId;
    }

    public String getmBranchType() {
        return mBranchType;
    }

    public String getmBranchStreetName() {
        return mBranchStreetName;
    }

    public String getmBranchBuildingNum() {
        return mBranchBuildingNum;
    }

    public String getmBranchFloorNum() {
        return mBranchFloorNum;
    }

    public String getmBranchAppartmentNum() {
        return mBranchAppartmentNum;
    }

    public String getmBranchAddressNotes() {
        return mBranchAddressNotes;
    }

    public String getmBranchPhoneNum() {
        return mBranchPhoneNum;
    }

    public String getmBranchLongitude() {
        return mBranchLongitude;
    }

    public String getmBranchLatitude() {
        return mBranchLatitude;
    }

    public Integer getmBranchRate() {
        return mBranchRate;
    }

    public RealmList<Appointment> getmAppointments() {
        return mAppointments;
    }

    public static class BranchBuilder {

        RealmList<Appointment> mAppointments;
        private Integer mBranchId;
        private String mBranchType;
        private String mBranchStreetName;
        private String mBranchBuildingNum;
        private String mBranchFloorNum;
        private String mBranchAppartmentNum;
        private String mBranchAddressNotes;
        private String mBranchPhoneNum;
        private String mBranchLongitude;
        private String mBranchLatitude;
        private Integer mBranchRate;

        public BranchBuilder branchId(Integer branchId) {
            mBranchId = branchId;
            return this;
        }

        public BranchBuilder branchType(String branchType) {
            mBranchType = branchType;
            return this;
        }

        public BranchBuilder branchStreetName(String branchStreetName) {
            mBranchStreetName = branchStreetName;
            return this;
        }

        public BranchBuilder branchBuildingNum(String branchBuildingNum) {
            mBranchBuildingNum = branchBuildingNum;
            return this;
        }

        public BranchBuilder branchFloorNum(String branchFloorNum) {
            mBranchFloorNum = branchFloorNum;
            return this;
        }

        public BranchBuilder branchAppartmentNum(String branchAppartmentNum) {
            mBranchAppartmentNum = branchAppartmentNum;
            return this;
        }

        public BranchBuilder branchAddressNotes(String branchAddressNotes) {
            mBranchAddressNotes = branchAddressNotes;
            return this;
        }

        public BranchBuilder branchPhoneNum(String branchPhoneNum) {
            mBranchPhoneNum = branchPhoneNum;
            return this;
        }

        public BranchBuilder branchLongitude(String branchLongitude) {
            mBranchLongitude = branchLongitude;
            return this;
        }

        public BranchBuilder branchLatitude(String branchLatitude) {
            mBranchLatitude = branchLatitude;
            return this;
        }

        public BranchBuilder branchRate(Integer branchRate) {
            mBranchRate = branchRate;
            return this;
        }

        public BranchBuilder appointments(RealmList<Appointment> appointments) {
            mAppointments = appointments;
            return this;
        }
    }
}
