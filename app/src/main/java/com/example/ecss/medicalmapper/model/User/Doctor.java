package com.example.ecss.medicalmapper.model.user;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Doctor extends RealmObject {

    @SerializedName("doctor_id")
    private String mDoctorId;

    @SerializedName("user_id")
    private String mUserId;

    @SerializedName("doctor_name")
    private String mDoctorName;

    @SerializedName("doctor_specialization")
    private String mDoctorSpecialization;

    @SerializedName("doctor_degree")
    private String mDoctorDegree;

    @SerializedName("doctor_overview")
    private String mDoctorOverview;

    @SerializedName("doctor_photo")
    private String mDoctorPhoto;

    @SerializedName("doctor_certifications")
    private String mDoctorCertifications;

    public String getmDoctorId() {
        return mDoctorId;
    }

    public void setmDoctorId(String mDoctorId) {
        this.mDoctorId = mDoctorId;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmDoctorName() {
        return mDoctorName;
    }

    public void setmDoctorName(String mDoctorName) {
        this.mDoctorName = mDoctorName;
    }

    public String getmDoctorSpecialization() {
        return mDoctorSpecialization;
    }

    public void setmDoctorSpecialization(String mDoctorSpecialization) {
        this.mDoctorSpecialization = mDoctorSpecialization;
    }

    public String getmDoctorDegree() {
        return mDoctorDegree;
    }

    public void setmDoctorDegree(String mDoctorDegree) {
        this.mDoctorDegree = mDoctorDegree;
    }

    public String getmDoctorOverview() {
        return mDoctorOverview;
    }

    public void setmDoctorOverview(String mDoctorOverview) {
        this.mDoctorOverview = mDoctorOverview;
    }

    public String getmDoctorPhoto() {
        return mDoctorPhoto;
    }

    public void setmDoctorPhoto(String mDoctorPhoto) {
        this.mDoctorPhoto = mDoctorPhoto;
    }

    public String getmDoctorCertifications() {
        return mDoctorCertifications;
    }

    public void setmDoctorCertifications(String mDoctorCertifications) {
        this.mDoctorCertifications = mDoctorCertifications;
    }
}
