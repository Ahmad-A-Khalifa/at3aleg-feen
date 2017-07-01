package com.example.ecss.medicalmapper.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Doctor /*extends RealmObject*/ implements Parcelable {

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel source) {
            return new Doctor(source);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

    @SerializedName("user_id")
    private Integer mUserId;

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

    public Doctor() {

    }

    public Doctor(Integer mUserId, String mDoctorName, String mDoctorSpecialization, String mDoctorDegree, String mDoctorOverview, String mDoctorPhoto, String mDoctorCertifications) {
        this.mUserId = mUserId;
        this.mDoctorName = mDoctorName;
        this.mDoctorSpecialization = mDoctorSpecialization;
        this.mDoctorDegree = mDoctorDegree;
        this.mDoctorOverview = mDoctorOverview;
        this.mDoctorPhoto = mDoctorPhoto;
        this.mDoctorCertifications = mDoctorCertifications;
    }

    public Doctor(Parcel in) {
        this.mUserId = in.readInt();
        this.mDoctorName = in.readString();
        this.mDoctorSpecialization = in.readString();
        this.mDoctorDegree = in.readString();
        this.mDoctorOverview = in.readString();
        this.mDoctorPhoto = in.readString();
        this.mDoctorCertifications = in.readString();
    }


    public Integer getUserId() {
        return mUserId;
    }

    public void setUserId(Integer UserId) {
        this.mUserId = UserId;
    }

    public String getDoctorName() {
        return mDoctorName;
    }

    public void setDoctorName(String DoctorName) {
        this.mDoctorName = DoctorName;
    }

    public String getDoctorSpecialization() {
        return mDoctorSpecialization;
    }

    public void setDoctorSpecialization(String DoctorSpecialization) {
        this.mDoctorSpecialization = DoctorSpecialization;
    }

    public String getDoctorDegree() {
        return mDoctorDegree;
    }

    public void setDoctorDegree(String DoctorDegree) {
        this.mDoctorDegree = DoctorDegree;
    }

    public String getDoctorOverview() {
        return mDoctorOverview;
    }

    public void setDoctorOverview(String DoctorOverview) {
        this.mDoctorOverview = DoctorOverview;
    }

    public String getDoctorPhoto() {
        return mDoctorPhoto;
    }

    public void setDoctorPhoto(String DoctorPhoto) {
        this.mDoctorPhoto = DoctorPhoto;
    }

    public String getDoctorCertifications() {
        return mDoctorCertifications;
    }

    public void setDoctorCertifications(String DoctorCertifications) {
        this.mDoctorCertifications = DoctorCertifications;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mUserId);
        dest.writeString(this.mDoctorName);
        dest.writeString(this.mDoctorSpecialization);
        dest.writeString(this.mDoctorDegree);
        dest.writeString(this.mDoctorOverview);
        dest.writeString(this.mDoctorPhoto);
        dest.writeString(this.mDoctorCertifications);
    }
}
