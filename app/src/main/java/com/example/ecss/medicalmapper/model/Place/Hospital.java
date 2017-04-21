package com.example.ecss.medicalmapper.model.place;


import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;


public class Hospital extends RealmObject implements MedicalPlace {

    public static final Creator<Hospital> CREATOR = new Creator<Hospital>() {
        @Override
        public Hospital createFromParcel(Parcel in) {
            return new Hospital(in);
        }

        @Override
        public Hospital[] newArray(int size) {
            return new Hospital[size];
        }
    };
    @SerializedName("Branch")
    RealmList<Branch> mBranches;
    @SerializedName("hospital_id")
    private Integer mHospitalId;
    @SerializedName("Hospital_name")
    private String mHospitalName;
    @SerializedName("is_government")
    private Boolean mIsGovernment;
    @SerializedName("hospital_emergency")
    private Boolean mHospitalEmergency;

    public Hospital() {
    }

    public Hospital(Parcel in) {
        this.mHospitalId = in.readInt();
        this.mHospitalName = in.readString();
        this.mIsGovernment = in.readByte() != 0;
        this.mHospitalEmergency = in.readByte() != 0;

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
        dest.writeInt(this.mHospitalId);
        dest.writeString(this.mHospitalName);
        dest.writeByte((byte) (this.mIsGovernment ? 1 : 0));
        dest.writeByte((byte) (this.mHospitalEmergency ? 1 : 0));
        dest.writeList(this.mBranches);
    }

    public Integer getmHospitalId() {
        return mHospitalId;
    }

    public void setmHospitalId(Integer mHospitalId) {
        this.mHospitalId = mHospitalId;
    }

    public String getmHospitalName() {
        return mHospitalName;
    }

    public void setmHospitalName(String mHospitalName) {
        this.mHospitalName = mHospitalName;
    }

    public Boolean getmIsGovernment() {
        return mIsGovernment;
    }

    public void setmIsGovernment(Boolean mIsGovernment) {
        this.mIsGovernment = mIsGovernment;
    }

    public Boolean getmHospitalEmergency() {
        return mHospitalEmergency;
    }

    public void setmHospitalEmergency(Boolean mHospitalEmergency) {
        this.mHospitalEmergency = mHospitalEmergency;
    }

    public RealmList<Branch> getmBranches() {
        return mBranches;
    }

    public void setmBranches(RealmList<Branch> mBranches) {
        this.mBranches = mBranches;
    }
}
