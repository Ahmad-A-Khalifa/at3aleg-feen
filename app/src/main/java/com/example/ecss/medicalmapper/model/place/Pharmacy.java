package com.example.ecss.medicalmapper.model.place;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Pharmacy /*extends RealmObject*/ implements MedicalPlace {


    public static final Creator<Pharmacy> CREATOR = new Creator<Pharmacy>() {
        @Override
        public Pharmacy createFromParcel(Parcel source) {
            return new Pharmacy(source);
        }

        @Override
        public Pharmacy[] newArray(int size) {
            return new Pharmacy[size];
        }
    };

    @SerializedName("branch")
    List<Branch> mBranches;

    @SerializedName("pharmacy_id")
    private Integer mPharmacyId;

    @SerializedName("pharmacy_name")
    private String mPharmacyName;

    @SerializedName("pharmacy_hot_line")
    private String mPharmacyHotline;

    @SerializedName("pharmacy_delivery")
    private Boolean mPharmacyDelivery;

    public Pharmacy() {
    }

    public Pharmacy(Parcel in) {
        this.mPharmacyId = in.readInt();
        this.mPharmacyName = in.readString();
        this.mPharmacyHotline = in.readString();
        this.mPharmacyDelivery = in.readByte() != 0;

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
        dest.writeInt(this.mPharmacyId);
        dest.writeString(this.mPharmacyName);
        dest.writeString(this.mPharmacyHotline);
        dest.writeByte((byte) (this.mPharmacyDelivery ? 1 : 0));
        dest.writeList(this.mBranches);
    }

    public Integer getPharmacyId() {
        return mPharmacyId;
    }

    public void setPharmacyId(Integer PharmacyId) {
        this.mPharmacyId = PharmacyId;
    }

    public String getPharmacyName() {
        return mPharmacyName;
    }

    public void setPharmacyName(String PharmacyName) {
        this.mPharmacyName = PharmacyName;
    }

    public String getPharmacyHotline() {
        return mPharmacyHotline;
    }

    public void setPharmacyHotline(String PharmacyHotline) {
        this.mPharmacyHotline = PharmacyHotline;
    }

    public Boolean getPharmacyDelivery() {
        return mPharmacyDelivery;
    }

    public void setPharmacyDelivery(Boolean PharmacyDelivery) {
        this.mPharmacyDelivery = PharmacyDelivery;
    }

    public List<Branch> getBranches() {
        return mBranches;
    }

    public void setBranches(List<Branch> Branches) {
        this.mBranches = Branches;
    }
}
