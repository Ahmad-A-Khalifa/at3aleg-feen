package com.example.ecss.medicalmapper.model.place;


import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;


public class Laboratory extends RealmObject implements MedicalPlace {

    @SerializedName("lab_id")
    private Integer mLabId;

    @SerializedName("lab_name")
    private String mLabName;

    @SerializedName("lab_specialization")
    private String mLabSpecialization;

    @SerializedName("lab_hot_line")
    private String mLabHotline;

    @SerializedName("Branch")
    RealmList<Branch> mBranches;

    public Laboratory() {
    }

    public Laboratory(Parcel in) {
        this.mLabId = in.readInt();
        this.mLabName = in.readString();
        this.mLabSpecialization = in.readString();
        this.mLabHotline = in.readString();

        RealmList<Branch> myList = null;
        in.readList(myList, List.class.getClassLoader());
        this.setmBranches(myList);
    }

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

    public Integer getmLabId() {
        return mLabId;
    }

    public void setmLabId(Integer mLabId) {
        this.mLabId = mLabId;
    }

    public String getmLabName() {
        return mLabName;
    }

    public void setmLabName(String mLabName) {
        this.mLabName = mLabName;
    }

    public String getmLabSpecialization() {
        return mLabSpecialization;
    }

    public void setmLabSpecialization(String mLabSpecialization) {
        this.mLabSpecialization = mLabSpecialization;
    }

    public String getmLabHotline() {
        return mLabHotline;
    }

    public void setmLabHotline(String mLabHotline) {
        this.mLabHotline = mLabHotline;
    }

    public RealmList<Branch> getmBranches() {
        return mBranches;
    }

    public void setmBranches(RealmList<Branch> mBranches) {
        this.mBranches = mBranches;
    }
}
