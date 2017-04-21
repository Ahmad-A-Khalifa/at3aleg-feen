package com.example.ecss.ebook.model;

import io.realm.RealmObject;


public class Appointments extends RealmObject {
    @SerializedName("appointment_id")
    private String mAppointmentId;

    @SerializedName("appointment_day_AR")
    private String mAppointmentDayAR;

    @SerializedName("appointment_day_EN")
    private String mAppointmentDayEN;

    @SerializedName("appointment_from")
    private String mAppointmentFrom;

    @SerializedName("appointment_to")
    private String mAppointmentTo;

    @SerializedName("branch_id")
    private String mBranchId;

    @SerializedName("branch_type")
    private String mBranchType;

    public String getmAppointmentId() {
        return mAppointmentId;
    }

    public void setmAppointmentId(String mAppointmentId) {
        this.mAppointmentId = mAppointmentId;
    }

    public String getmAppointmentDayAR() {
        return mAppointmentDayAR;
    }

    public void setmAppointmentDayAR(String mAppointmentDayAR) {
        this.mAppointmentDayAR = mAppointmentDayAR;
    }

    public String getmAppointmentDayEN() {
        return mAppointmentDayEN;
    }

    public void setmAppointmentDayEN(String mAppointmentDayEN) {
        this.mAppointmentDayEN = mAppointmentDayEN;
    }

    public String getmAppointmentFrom() {
        return mAppointmentFrom;
    }

    public void setmAppointmentFrom(String mAppointmentFrom) {
        this.mAppointmentFrom = mAppointmentFrom;
    }

    public String getmAppointmentTo() {
        return mAppointmentTo;
    }

    public void setmAppointmentTo(String mAppointmentTo) {
        this.mAppointmentTo = mAppointmentTo;
    }

    public String getmBranchId() {
        return mBranchId;
    }

    public void setmBranchId(String mBranchId) {
        this.mBranchId = mBranchId;
    }

    public String getmBranchType() {
        return mBranchType;
    }

    public void setmBranchType(String mBranchType) {
        this.mBranchType = mBranchType;
    }
}
