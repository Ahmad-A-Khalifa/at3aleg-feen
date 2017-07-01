package com.example.ecss.medicalmapper.model.place;


import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Hospital /*extends RealmObject*/ implements MedicalPlace {

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

    @SerializedName("branch")
    private List<Branch> mBranches;

    @SerializedName("hospital_id")
    private Integer mHospitalId;

    @SerializedName("hospital_name")
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

        List<Branch> myList = new ArrayList<>();
        in.readList(myList, getClass().getClassLoader());
        this.mBranches = myList;
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

    public Integer getHospitalId() {
        return mHospitalId;
    }

    public void setHospitalId(Integer HospitalId) {
        this.mHospitalId = HospitalId;
    }

    public String getHospitalName() {
        return mHospitalName;
    }

    public void setHospitalName(String HospitalName) {
        this.mHospitalName = HospitalName;
    }

    public Boolean getIsGovernment() {
        return mIsGovernment;
    }

    public void setIsGovernment(Boolean IsGovernment) {
        this.mIsGovernment = IsGovernment;
    }

    public Boolean getHospitalEmergency() {
        return mHospitalEmergency;
    }

    public void setHospitalEmergency(Boolean HospitalEmergency) {
        this.mHospitalEmergency = HospitalEmergency;
    }

    public List<Branch> getBranches() {
        return mBranches;
    }

    public void setBranches(List<Branch> Branches) {
        this.mBranches = Branches;
    }
}
