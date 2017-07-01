package com.example.ecss.medicalmapper.model.place;


import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Laboratory /*extends RealmObject*/ implements MedicalPlace {

    public static final Creator<Laboratory> CREATOR = new Creator<Laboratory>() {
        @Override
        public Laboratory createFromParcel(Parcel in) {
            return new Laboratory(in);
        }

        @Override
        public Laboratory[] newArray(int size) {
            return new Laboratory[size];
        }
    };

    @SerializedName("branch")
    List<Branch> mBranches;

    @SerializedName("lab_id")
    private Integer mLabId;

    @SerializedName("lab_name")
    private String mLabName;

    @SerializedName("lab_specialization")
    private String mLabSpecialization;

    @SerializedName("lab_hot_line")
    private String mLabHotline;

    public Laboratory() {
    }

    public Laboratory(Parcel in) {
        this.mLabId = in.readInt();
        this.mLabName = in.readString();
        this.mLabSpecialization = in.readString();
        this.mLabHotline = in.readString();

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

        dest.writeInt(this.mLabId);
        dest.writeString(this.mLabName);
        dest.writeString(this.mLabSpecialization);
        dest.writeString(this.mLabHotline);
        dest.writeList(this.mBranches);
    }

    public Integer getLabId() {
        return mLabId;
    }

    public void setLabId(Integer LabId) {
        this.mLabId = LabId;
    }

    public String getLabName() {
        return mLabName;
    }

    public void setLabName(String LabName) {
        this.mLabName = LabName;
    }

    public String getLabSpecialization() {
        return mLabSpecialization;
    }

    public void setLabSpecialization(String LabSpecialization) {
        this.mLabSpecialization = LabSpecialization;
    }

    public String getLabHotline() {
        return mLabHotline;
    }

    public void setLabHotline(String LabHotline) {
        this.mLabHotline = LabHotline;
    }

    public List<Branch> getBranches() {
        return mBranches;
    }

    public void setBranches(List<Branch> Branches) {
        this.mBranches = Branches;
    }
}
