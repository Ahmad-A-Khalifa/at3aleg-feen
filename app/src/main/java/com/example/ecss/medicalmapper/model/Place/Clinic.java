package com.example.ecss.medicalmapper.model.place;


import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Clinic extends RealmObject implements MedicalPlace {


    public static final Creator<Clinic> CREATOR = new Creator<Clinic>() {
        @Override
        public Clinic createFromParcel(android.os.Parcel in) {
            return new Clinic(in);
        }

        @Override
        public Clinic[] newArray(int size) {
            return new Clinic[size];
        }
    };
    @SerializedName("Branch")
    RealmList<Branch> mBranches;
    @SerializedName("clinic_id")
    private Integer mClinicId;
    @SerializedName("clinic_name")
    private String mClinicName;
    @SerializedName("clinic_specialization")
    private String mClinicSpecialization;

    public Clinic() {
    }

    public Clinic(Parcel in) {
        this.mClinicId = in.readInt();
        this.mClinicName = in.readString();
        this.mClinicSpecialization = in.readString();

        RealmList<Branch> myList = null;
        in.readList(myList, List.class.getClassLoader());
        this.setmBranches(myList);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mClinicId);
        dest.writeString(this.mClinicName);
        dest.writeString(this.mClinicSpecialization);
        dest.writeList(mBranches);
    }

    public Integer getmClinicId() {
        return mClinicId;
    }

    public void setmClinicId(Integer mClinicId) {
        this.mClinicId = mClinicId;
    }

    public String getmClinicName() {
        return mClinicName;
    }

    public void setmClinicName(String mClinicName) {
        this.mClinicName = mClinicName;
    }

    public String getmClinicSpecialization() {
        return mClinicSpecialization;
    }

    public void setmClinicSpecialization(String mClinicSpecialization) {
        this.mClinicSpecialization = mClinicSpecialization;
    }

    public RealmList<Branch> getmBranches() {
        return mBranches;
    }

    public void setmBranches(RealmList<Branch> mBranches) {
        this.mBranches = mBranches;
    }
}
